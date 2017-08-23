package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.PeriksaUpdateActivity;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.R;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Periksa;

import java.util.List;

/**
 * Created by santoso on 8/15/17.
 */

public class PeriksaRecycleViewAdapter extends RecyclerView.Adapter<PeriksaRecycleViewAdapter.ViewHolder> {
    private Context context;
    private List<Periksa> periksa;

    public PeriksaRecycleViewAdapter(Context context, List<Periksa> periksa) {
        this.context = context;
        this.periksa = periksa;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_periksa, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Periksa p = periksa.get(position);
        holder.txtNomor.setText(p.getNomor());
        holder.txtTanggal.setText(p.getTanggal());
        holder.txtKodeDokter.setText(p.getKodeDokter());
        holder.txtKodePasien.setText(p.getKodePasien());
        holder.txtPenyakit.setText(p.getPenyakit());
        holder.txtNamaDokter.setText(p.getNamaDokter());
        holder.txtNamaPasien.setText(p.getNamaPasien());

    }

    @Override
    public int getItemCount() {
        return periksa.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtNomor;
        private TextView txtTanggal;
        private TextView txtKodeDokter;
        private TextView txtKodePasien;
        private TextView txtPenyakit;
        private TextView txtNamaDokter;
        private TextView txtNamaPasien;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomor = (TextView) itemView.findViewById(R.id.txt_nomor);
            txtTanggal = (TextView) itemView.findViewById(R.id.txt_tanggal);
            txtKodeDokter = (TextView) itemView.findViewById(R.id.txt_kode_dokter);
            txtKodePasien = (TextView) itemView.findViewById(R.id.txt_kode_pasien);
            txtPenyakit = (TextView) itemView.findViewById(R.id.txt_penyakit);
            txtNamaDokter = (TextView) itemView.findViewById(R.id.txt_nama_dokter);
            txtNamaPasien = (TextView) itemView.findViewById(R.id.txt_nama_pasien);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String nomor = txtNomor.getText().toString();
            String tanggal = txtTanggal.getText().toString();
            String kodeDokter = txtKodeDokter.getText().toString();
            String kodePasien = txtKodePasien.getText().toString();
            String penyakit = txtPenyakit.getText().toString();
            String namaDokter = txtNamaDokter.getText().toString();
            String namaPasien = txtNamaPasien.getText().toString();


            Intent i = new Intent(context, PeriksaUpdateActivity.class);
            i.putExtra("nomor",nomor);
            i.putExtra("tanggal",tanggal);
            i.putExtra("kodeDokter",kodeDokter);
            i.putExtra("kodePasien",kodePasien);
            i.putExtra("penyakit",penyakit);
            i.putExtra("namaDokter",namaDokter);
            i.putExtra("namaPasien",namaPasien);
            context.startActivity(i);;
        }
    }
}
