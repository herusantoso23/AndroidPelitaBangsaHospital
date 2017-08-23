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

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter.PasienRecycleViewAdapter;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.PasienRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Pasien;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.PasienValue;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.R.id.fab;

/**
 * Created by santoso on 8/15/17.
 */

public class KelompokFragment extends Fragment {
    private View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_kelompok, container, false);


        return myView;

    }

}
