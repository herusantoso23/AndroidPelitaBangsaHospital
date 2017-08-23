package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.DokterUpdateActivity;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.R;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Dokter;

import java.util.List;

/**
 * Created by santoso on 8/15/17.
 */

public class DokterRecycleViewAdapter extends RecyclerView.Adapter<DokterRecycleViewAdapter.ViewHolder> {
    private Context context;
    private List<Dokter> dokter;

    public DokterRecycleViewAdapter(Context context, List<Dokter> dokter) {
        this.context = context;
        this.dokter = dokter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dokter, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dokter d = dokter.get(position);
        holder.txtKodeDokter.setText(d.getKodeDokter());
        holder.txtNama.setText(d.getNama());
        holder.txtTelepon.setText(d.getTelepon());
        holder.txtSpesialis.setText(d.getSpesialis());

    }

    @Override
    public int getItemCount() {
        return dokter.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtKodeDokter;
        private TextView txtNama;
        private TextView txtTelepon;
        private TextView txtSpesialis;

        public ViewHolder(View itemView) {
            super(itemView);

            txtKodeDokter = (TextView) itemView.findViewById(R.id.txt_kodeDokter);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtTelepon = (TextView) itemView.findViewById(R.id.txt_telepon);
            txtSpesialis = (TextView) itemView.findViewById(R.id.txt_spesialis);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String kodeDokter = txtKodeDokter.getText().toString();
            String nama = txtNama.getText().toString();
            String telepon = txtTelepon.getText().toString();
            String spesialis = txtSpesialis.getText().toString();

            Intent i = new Intent(context, DokterUpdateActivity.class);
            i.putExtra("kodeDokter",kodeDokter);
            i.putExtra("nama",nama);
            i.putExtra("telepon",telepon);
            i.putExtra("spesialis",spesialis);
            context.startActivity(i);;
        }
    }
}
