package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.ObatRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ObatValue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ObatAddActivity extends AppCompatActivity {
    private ProgressDialog progress;
    String kodeObat, nama, jenis;

    private EditText editKodeObat;
    private EditText editNama;
    private EditText editJenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obat_add);

        editKodeObat = (EditText) findViewById(R.id.edit_kode_obat);
        editNama = (EditText) findViewById(R.id.edit_nama);
        editJenis = (EditText) findViewById(R.id.edit_jenis);
    }

    public void inisiasi(){
        kodeObat = editKodeObat.getText().toString();
        nama = editNama.getText().toString();
        jenis = editJenis.getText().toString();
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

        ObatRegisterApi api = retrofit.create(ObatRegisterApi.class);
        Call<ObatValue> call = api.add(kodeObat, nama, jenis);

        call.enqueue(new Callback<ObatValue>() {
            @Override
            public void onResponse(Call<ObatValue> call, Response<ObatValue> response) {
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
            public void onFailure(Call<ObatValue> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
