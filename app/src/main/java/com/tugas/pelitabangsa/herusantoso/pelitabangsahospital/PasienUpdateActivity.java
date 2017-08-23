package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.ObatRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.PasienRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ObatValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PasienValue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PasienUpdateActivity extends AppCompatActivity {
    private ProgressDialog progress;
    String kodePasien, nama, umur, alamat;

    private EditText editKodePasien;
    private EditText editNama;
    private EditText editUmur;
    private EditText editAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasien_update);


        editKodePasien = (EditText) findViewById(R.id.edit_kode_pasien);
        editNama = (EditText) findViewById(R.id.edit_nama);
        editUmur = (EditText) findViewById(R.id.edit_umur);
        editAlamat = (EditText) findViewById(R.id.edit_alamat);

        editKodePasien.setText(getIntent().getStringExtra("kodePasien"));
        editNama.setText(getIntent().getStringExtra("nama"));
        editUmur.setText(getIntent().getStringExtra("umur"));
        editAlamat.setText(getIntent().getStringExtra("alamat"));
    }

    public void inisiasi(){
        kodePasien = editKodePasien.getText().toString();
        nama = editNama.getText().toString();
        umur = editUmur.getText().toString();
        alamat = editAlamat.getText().toString();
    }

    public void simpanOnClick(View v){
        //menampilakn progress dialog
        inisiasi();
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading");
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppDefinition.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PasienRegisterApi api = retrofit.create(PasienRegisterApi.class);
        Call<PasienValue> call = api.update(kodePasien, nama, umur, alamat);

        call.enqueue(new Callback<PasienValue>() {
            @Override
            public void onResponse(Call<PasienValue> call, Response<PasienValue> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                progress.dismiss();

                if(value.equals("1")){
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PasienValue> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Peringatan");
                alertDialogBuilder
                        .setMessage("Apakah Anda yakin ingin mengapus data ini?")
                        .setCancelable(false)
                        .setPositiveButton("Hapus",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                String kodePasien = editKodePasien.getText().toString();
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(AppDefinition.URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                PasienRegisterApi api = retrofit.create(PasienRegisterApi.class);
                                Call<PasienValue> call = api.delete(kodePasien);
                                call.enqueue(new Callback<PasienValue>() {
                                    @Override
                                    public void onResponse(Call<PasienValue> call, Response<PasienValue> response) {
                                        String value = response.body().getValue();
                                        String message = response.body().getMessage();
                                        if (value.equals("1")) {
                                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<PasienValue> call, Throwable t) {
                                        t.printStackTrace();
                                        Toast.makeText(getApplicationContext(), "Jaringan Error!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Batal",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
