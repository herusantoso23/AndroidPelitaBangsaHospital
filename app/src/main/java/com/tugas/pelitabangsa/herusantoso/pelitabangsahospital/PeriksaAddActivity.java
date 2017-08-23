package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.DokterRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.PeriksaRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.DokterValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Periksa;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PeriksaValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.edit;

public class PeriksaAddActivity extends AppCompatActivity {
    private ProgressDialog progress;

    private EditText editNomor;
    private EditText editBulan;
    private EditText editHari;
    private EditText editTahun;
    private EditText editDokter;
    private EditText editPasien;
    private EditText editPenyakit;

    private List<Periksa> periksa = new ArrayList<>();

    String nomor, hari, bulan, tahun, kodeDokter, kodePasien, penyakit, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periksa_add);

        editNomor = (EditText) findViewById(R.id.edit_nomor);
        editBulan = (EditText) findViewById(R.id.edit_bulan);
        editHari = (EditText) findViewById(R.id.edit_hari);
        editTahun = (EditText) findViewById(R.id.edit_tahun);
        editDokter = (EditText) findViewById(R.id.edit_dokter);
        editPasien = (EditText) findViewById(R.id.edit_pasien);
        editPenyakit = (EditText) findViewById(R.id.edit_penyakit);



        DateFormat formatTahun = new SimpleDateFormat("yyyy");
        DateFormat formatBulan = new SimpleDateFormat("MM");
        DateFormat formatHari = new SimpleDateFormat("dd");
        //String now = df.format(new Date());

        editHari.setText(formatHari.format(new Date()));
        editBulan.setText(formatBulan.format(new Date()));
        editTahun.setText(formatTahun.format(new Date()));


    }

    public void inisiasi(){
        nomor = editNomor.getText().toString();
        hari = editHari.getText().toString();
        bulan = editBulan.getText().toString();
        tahun = editTahun.getText().toString();
        kodeDokter = editDokter.getText().toString();
        kodePasien = editPasien.getText().toString();
        penyakit = editPenyakit.getText().toString();

        tanggal = tahun + "-" + bulan + "-" + hari;



    }

    public void simpanOnClick(View v){
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading");
        progress.show();

        inisiasi();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppDefinition.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PeriksaRegisterApi api = retrofit.create(PeriksaRegisterApi.class);
        Call<PeriksaValue> call = api.add(nomor, tanggal, kodeDokter, kodePasien, penyakit);

        call.enqueue(new Callback<PeriksaValue>() {
            @Override
            public void onResponse(Call<PeriksaValue> call, Response<PeriksaValue> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                progress.dismiss();

                if(value.equals("1")){
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    //finish();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PeriksaAddActivity.this);
                    alertDialogBuilder.setTitle("Peringatan");
                    alertDialogBuilder
                            .setMessage("Apakah akan menambahkan resep ?")
                            .setCancelable(false)
                            .setPositiveButton("Resep",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    Intent i = new Intent(getApplicationContext(), ResepAddActivity.class);
                                    i.putExtra("id", nomor);
                                    startActivity(i);
                                    finish();
                                }
                            })
                            .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                    finish();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                } else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PeriksaValue> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }


        });

    }
}
