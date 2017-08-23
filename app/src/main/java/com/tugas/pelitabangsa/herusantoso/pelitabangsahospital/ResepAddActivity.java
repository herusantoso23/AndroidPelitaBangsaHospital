package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter.ResepAddRecycleViewAdapter;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter.ResepRecycleViewAdapter;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.ObatRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.ResepPeriksaRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Obat;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ObatValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Resep;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ResepValue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResepAddActivity extends AppCompatActivity {
    private List<Obat> reseps = new ArrayList<>();
    private ResepAddRecycleViewAdapter viewAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Resep r;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep_add);
        r = new Resep();
        r.setId(getIntent().getStringExtra("id"));

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        viewAdapter = new ResepAddRecycleViewAdapter(this, reseps);
        RecyclerView.LayoutManager aLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(aLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        loadDataResep();

    }

    @Override
    public void onResume(){
        super.onResume();
        loadDataResep();
    }

    private void loadDataResep(){
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppDefinition.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ObatRegisterApi api = retrofit.create(ObatRegisterApi.class);
        Call<ObatValue> call = api.view();

        call.enqueue(new Callback<ObatValue>() {
            @Override
            public void onResponse(Call<ObatValue> call, Response<ObatValue> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                if(value.equals("1")){
                    reseps = response.body().getResult();
                    viewAdapter = new ResepAddRecycleViewAdapter(ResepAddActivity.this, reseps);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<ObatValue> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void selesaiOnClick(View v){
        finish();
    }
}
