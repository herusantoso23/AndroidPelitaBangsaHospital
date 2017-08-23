package com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.PasienUpdateActivity;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.R;
import com.tugas.pelitabangsa.herusantoso.pelitabangsahospital.model.Pasien;

import java.util.List;

/**
 * Created by santoso on 8/15/17.
 */

public class PasienRecycleViewAdapter extends RecyclerView.Adapter<PasienRecycleViewAdapter.ViewHolder> {
    private Context context;
    private List<Pasien> pasien;

    public PasienRecycleViewAdapter(Context context, List<Pasien> pasien) {
        this.context = context;
        this.pasien = pasien;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pasien, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pasien p = pasien.get(position);
        holder.txtKodePasien.setText(p.getKodePasien());
        holder.txtNama.setText(p.getNama());
        holder.txtUmur.setText(p.getUmur());
        holder.txtAlamat.setText(p.getAlamat());

    }

    @Override
    public int getItemCount() {
        return pasien.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtKodePasien;
        private TextView txtNama;
        private TextView txtUmur;
        private TextView txtAlamat;

        public ViewHolder(View itemView) {
            super(itemView);

            txtKodePasien = (TextView) itemView.findViewById(R.id.txt_pasien);
            txtNama = (TextView) itemView.findViewById(R.id.txt_nama);
            txtUmur = (TextView) itemView.findViewById(R.id.txt_umur);
            txtAlamat = (TextView) itemView.findViewById(R.id.txt_alamat);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String kodePasien = txtKodePasien.getText().toString();
            String nama = txtNama.getText().toString();
            String umur = txtUmur.getText().toString();
            String alamat = txtAlamat.getText().toString();

            Intent i = new Intent(context, PasienUpdateActivity.class);
            i.putExtra("kodePasien",kodePasien);
            i.putExtra("nama",nama);
            i.putExtra("umur",umur);
            i.putExtra("alamat",alamat);
            context.startActivity(i);;
        }
    }
}
