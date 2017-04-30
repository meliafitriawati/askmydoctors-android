package com.askmydoctors.askmydoctors.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.models.Chat;
import com.askmydoctors.askmydoctors.models.Pertanyaan;
import com.askmydoctors.askmydoctors.models.Spesialisasi;
import com.askmydoctors.askmydoctors.views.DaftarPertanyaanActivity;
import com.askmydoctors.askmydoctors.views.DetailPertanyaanActivity;

import java.util.List;

/**
 * Created by meliafitriawati on 4/3/2017.
 */

public class PertanyaanAdapter extends RecyclerView.Adapter<PertanyaanAdapter.MyViewHolder>  {
    private List<Pertanyaan> pertanyaanList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView judul, pertanyaan, tanggal;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            judul = (TextView) view.findViewById(R.id.ivJudul);
            pertanyaan = (TextView) view.findViewById(R.id.ivPertanyaan);
            tanggal = (TextView) view.findViewById(R.id.ivTanggal);
        }

    }

    public PertanyaanAdapter(List<Pertanyaan> pertanyaanList) {
        this.pertanyaanList = pertanyaanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pertanyaan, parent, false);

        return new PertanyaanAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PertanyaanAdapter.MyViewHolder holder, int position) {
        Pertanyaan pertanyaan = pertanyaanList.get(position);
        holder.pertanyaan.setTag(pertanyaan.getId());
        if (pertanyaan.getJudul().length() > 20){
            holder.judul.setText(pertanyaan.getJudul().substring(0, 20) + "...");
        }else{
            holder.judul.setText(pertanyaan.getJudul());
        }

        if (pertanyaan.getPertanyaan().length() > 100){
            holder.pertanyaan.setText(pertanyaan.getPertanyaan().substring(0, 100) + "...");
        }else{
            holder.pertanyaan.setText(pertanyaan.getPertanyaan());
        }

        holder.tanggal.setText("4/4/17");

        holder.pertanyaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(v.getTag());

                Intent i = new Intent(context, DetailPertanyaanActivity.class);
                i.putExtra("id_pertanyaan", id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pertanyaanList.size();
    }

    public List<Pertanyaan> getList() {
        return pertanyaanList;
    }

    public void addPertanyaan(Pertanyaan pertanyaan) {
        pertanyaanList.add(pertanyaan);
    }
}
