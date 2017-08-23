package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter.PeriksaRecycleViewAdapter;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.PeriksaRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Periksa;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PeriksaValue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by santoso on 8/15/17.
 */

public class PeriksaFragment extends Fragment {
    private View myView;

    private List<Periksa> periksa = new ArrayList<>();
    private PeriksaRecycleViewAdapter viewAdapter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_periksa, container, false);

        progressBar = (ProgressBar) myView.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) myView.findViewById(R.id.recycleView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);

        fab = (FloatingActionButton) myView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PeriksaAddActivity.class);
                startActivity(i);
            }
        });

        loadDataPasien();
        return myView;
    }

    @Override
    public void onResume(){
        super.onResume();
        loadDataPasien();
    }

    private void loadDataPasien(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppDefinition.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PeriksaRegisterApi api = retrofit.create(PeriksaRegisterApi.class);
        Call<PeriksaValue> call = api.view();

        call.enqueue(new Callback<PeriksaValue>() {
            @Override
            public void onResponse(Call<PeriksaValue> call, Response<PeriksaValue> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);

                if(value.equals("1")){
                    periksa = response.body().getResult();
                    viewAdapter = new PeriksaRecycleViewAdapter(getActivity(), periksa);
                    recyclerView.setAdapter(viewAdapter);
                }
            }

            @Override
            public void onFailure(Call<PeriksaValue> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(myView.GONE);
                Toast.makeText(getActivity().getApplicationContext(), "Jaringan Error !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
