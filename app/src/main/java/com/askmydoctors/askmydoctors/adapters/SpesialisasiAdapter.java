package com.askmydoctors.askmydoctors.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.models.Chat;
import com.askmydoctors.askmydoctors.models.Spesialisasi;
import com.askmydoctors.askmydoctors.views.DaftarPertanyaanActivity;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class SpesialisasiAdapter extends RecyclerView.Adapter<SpesialisasiAdapter.MyViewHolder>  {
    private List<Spesialisasi> spesialisasiList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView spesialisasi;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            spesialisasi = (TextView) view.findViewById(R.id.namaSpesialisasi);
        }
    }

    public SpesialisasiAdapter(List<Spesialisasi> spesialisasiList) {
        this.spesialisasiList = spesialisasiList;
    }

    @Override
    public SpesialisasiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diskusi, parent, false);

        return new SpesialisasiAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SpesialisasiAdapter.MyViewHolder holder, int position) {
        Spesialisasi spesialisasi = spesialisasiList.get(position);
        holder.spesialisasi.setTag(spesialisasi.getId());
        holder.spesialisasi.setText("Konsultasi "+ spesialisasi.getSpesialisasi());
        holder.spesialisasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(v.getTag());
                Intent i = new Intent(context, DaftarPertanyaanActivity.class);
                i.putExtra("id_spesialisasi", id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return spesialisasiList.size();
    }

    public List<Spesialisasi> getList() {
        return spesialisasiList;
    }

    public void addSpesialisasi(Spesialisasi spesialisasi) {
        spesialisasiList.add(spesialisasi);
    }
}
