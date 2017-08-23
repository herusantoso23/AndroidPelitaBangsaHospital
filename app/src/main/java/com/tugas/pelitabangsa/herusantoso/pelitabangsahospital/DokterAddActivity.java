package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.DokterRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.DokterValue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.password;

public class DokterAddActivity extends AppCompatActivity {
    private ProgressDialog progress;
    String kodeDokter, nama, telepon, spesialis;

    private EditText editKodeDokter;
    private EditText editNama;
    private EditText editTelepon;
    private EditText editSpesialis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter_add);

        editKodeDokter = (EditText) findViewById(R.id.edit_kode_dokter);
        editNama = (EditText) findViewById(R.id.edit_nama);
        editTelepon = (EditText) findViewById(R.id.edit_telepon);
        editSpesialis = (EditText) findViewById(R.id.edit_spesialis);

        inisiasi();
    }

    public void inisiasi(){
        kodeDokter = editKodeDokter.getText().toString();
        nama = editNama.getText().toString();
        telepon = editTelepon.getText().toString();
        spesialis = editSpesialis.getText().toString();
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

        DokterRegisterApi api = retrofit.create(DokterRegisterApi.class);
        Call<DokterValue> call = api.add(kodeDokter, nama, telepon, spesialis);

        call.enqueue(new Callback<DokterValue>() {
            @Override
            public void onResponse(Call<DokterValue> call, Response<DokterValue> response) {
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
            public void onFailure(Call<DokterValue> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
