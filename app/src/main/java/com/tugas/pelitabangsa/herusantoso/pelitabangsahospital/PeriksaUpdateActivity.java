package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter.ResepRecycleViewAdapter;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.ResepPeriksaRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Resep;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ResepValue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeriksaUpdateActivity extends AppCompatActivity {


    private EditText editNomor;

    private EditText editBulan;
    private EditText editHari;
    private EditText editTahun;
    private EditText editDokter;
    private EditText editPasien;
    private EditText editPenyakit;
    private EditText editNamaDokter;
    private EditText editNamaPasien;



    private List<Resep> reseps = new ArrayList<>();
    private ResepRecycleViewAdapter viewAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periksa_update);


        editNomor = (EditText) findViewById(R.id.edit_nomor);

        editBulan = (EditText) findViewById(R.id.edit_bulan);
        editHari = (EditText) findViewById(R.id.edit_hari);
        editTahun = (EditText) findViewById(R.id.edit_tahun);
        editDokter = (EditText) findViewById(R.id.edit_dokter);
        editPasien = (EditText) findViewById(R.id.edit_pasien);
        editPenyakit = (EditText) findViewById(R.id.edit_penyakit);
        editNamaDokter = (EditText) findViewById(R.id.edit_nama_dokter);
        editNamaPasien = (EditText) findViewById(R.id.edit_nama_pasien);


        editNomor.setText(getIntent().getStringExtra("nomor"));

        String hari = getIntent().getStringExtra("tanggal").substring(8, 10);
        String bulan = getIntent().getStringExtra("tanggal").substring(5, 7);
        String tahun = getIntent().getStringExtra("tanggal").substring(0, 4);


        editHari.setText(hari);
        editBulan.setText(bulan);
        editTahun.setText(tahun);
        editDokter.setText(getIntent().getStringExtra("kodeDokter"));
        editPasien.setText(getIntent().getStringExtra("namaDokter"));
        editPenyakit.setText(getIntent().getStringExtra("penyakit"));
        editNamaDokter.setText(getIntent().getStringExtra("namaDokter"));
        editNamaPasien.setText(getIntent().getStringExtra("namaPasien"));


        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        viewAdapter = new ResepRecycleViewAdapter(this, reseps);
        RecyclerView.LayoutManager aLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(aLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataResep(editNomor.getText().toString());

    }


    @Override
    public void onResume(){
        super.onResume();
        loadDataResep(editNomor.getText().toString());
    }

    private void loadDataResep(String newText){
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppDefinition.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ResepPeriksaRegisterApi api = retrofit.create(ResepPeriksaRegisterApi.class);
        Call<ResepValue> call = api.search(newText);

        call.enqueue(new Callback<ResepValue>() {
            @Override
            public void onResponse(Call<ResepValue> call, Response<ResepValue> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                if(value.equals("1")){
                    reseps = response.body().getResult();
                    viewAdapter = new ResepRecycleViewAdapter(PeriksaUpdateActivity.this, reseps);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResepValue> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
