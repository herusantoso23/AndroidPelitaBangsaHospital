package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.ObatUpdateActivity;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.R;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Obat;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Resep;

import java.util.List;

/**
 * Created by santoso on 8/15/17.
 */

public class ResepRecycleViewAdapter extends RecyclerView.Adapter<ResepRecycleViewAdapter.ViewHolder> {
    private Context context;
    private List<Resep> resep;

    public ResepRecycleViewAdapter(Context context, List<Resep> resep) {
        this.context = context;
        this.resep = resep;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_resep, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Resep d = resep.get(position);
        holder.txtNomor.setText(d.getNomor());
        holder.txtKodeObat.setText(d.getKodeObat());
        holder.txtNama.setText(d.getNama());
        holder.txtJenis.setText(d.getJenis());

    }

    @Override
    public int getItemCount() {
        return resep.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNomor;
        private TextView txtKodeObat;
        private TextView txtNama;
        private TextView txtJenis;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomor = (TextView) itemView.findViewById(R.id.txt_nomor);
            txtKodeObat = (TextView) itemView.findViewById(R.id.txt_kode_Obat);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtJenis = (TextView) itemView.findViewById(R.id.txt_jenis);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String kodeObat = txtKodeObat.getText().toString();
            String nama = txtNama.getText().toString();
            String jenis = txtJenis.getText().toString();

            Intent i = new Intent(context, ObatUpdateActivity.class);
            i.putExtra("kodeObat",kodeObat);
            i.putExtra("nama",nama);
            i.putExtra("jenis",jenis);
            context.startActivity(i);
        }
    }
}
