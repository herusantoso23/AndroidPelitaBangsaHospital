package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.ObatUpdateActivity;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.PeriksaAddActivity;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.PeriksaFragment;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.R;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.ResepAddActivity;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.DokterRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.api.ResepPeriksaRegisterApi;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.common.AppDefinition;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.DokterValue;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Obat;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Resep;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.ResepValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.message;

/**
 * Created by santoso on 8/15/17.
 */

public class ResepAddRecycleViewAdapter extends RecyclerView.Adapter<ResepAddRecycleViewAdapter.ViewHolder> {
    private Context context;
    private List<Obat> resep;

    public ResepAddRecycleViewAdapter(Context context, List<Obat> resep) {

        this.context = context;
        this.resep = resep;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_resep_add, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Obat d = resep.get(position);
        holder.txtKodeObat.setText(d.getKodeObat());
        holder.txtNama.setText(d.getNama());
        holder.txtJenis.setText(d.getJenis());

    }

    @Override
    public int getItemCount() {
        return resep.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtId;
        private TextView txtKodeObat;
        private TextView txtNama;
        private TextView txtJenis;
        //private ProgressDialog progress;

        public ViewHolder(View itemView) {
            super(itemView);
            txtId = (TextView) itemView.findViewById(R.id.id);
            txtKodeObat = (TextView) itemView.findViewById(R.id.txt_kode_Obat);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtJenis = (TextView) itemView.findViewById(R.id.txt_jenis);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            insertResep();
        }

        private void insertResep(){
            Resep r = new Resep();
            final String id = r.getId().toString();
            String kodeObat = txtKodeObat.getText().toString();
            String nama = txtNama.getText().toString();
            String jenis = txtJenis.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(AppDefinition.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ResepPeriksaRegisterApi api = retrofit.create(ResepPeriksaRegisterApi.class);
            Call<ResepValue> call = api.add(id, kodeObat);

            call.enqueue(new Callback<ResepValue>() {
                @Override
                public void onResponse(Call<ResepValue> call, Response<ResepValue> response) {
                    String value = response.body().getValue();
                    String message = response.body().getMessage();

                    if(value.equals("1")){
                        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResepValue> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(context.getApplicationContext(), "Jaringan Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
