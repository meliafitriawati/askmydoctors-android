package com.askmydoctors.askmydoctors.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.models.Dokter;
import com.askmydoctors.askmydoctors.models.Spesialisasi;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.views.DaftarPertanyaanActivity;
import com.askmydoctors.askmydoctors.views.ProfilDokterActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by meliafitriawati on 5/9/2017.
 */

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.MyViewHolder> {
    private List<Dokter> dokterList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama_dokter, spesialisasi;
        CircleImageView img;
        RelativeLayout rlayout;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            nama_dokter = (TextView) view.findViewById(R.id.namaDokter);
            spesialisasi = (TextView) view.findViewById(R.id.namaSpesialisasi);
            img = (CircleImageView) view.findViewById(R.id.ivGambarDokter);
            rlayout = (RelativeLayout) view.findViewById(R.id.RelaLayout);
        }
    }

    public DokterAdapter(List<Dokter> dokterList) {
        this.dokterList = dokterList;
    }

    @Override
    public DokterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dokter, parent, false);

        return new DokterAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DokterAdapter.MyViewHolder holder, int position) {
        Dokter dokter = dokterList.get(position);
        holder.rlayout.setTag(dokter.getUsername());
        holder.nama_dokter.setText("Dokter "+ dokter.getNama());
        holder.spesialisasi.setText("Spesialis "+ dokter.getNama_spesialisasi());

        String img = dokter.getImg();
        String url_img = Config.URL_WEB + "assets/img/dokter/" + img;
        Log.d("url_img", url_img);

        Picasso.with(context)
                .load(url_img)
                .into(holder.img);

        holder.rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(v.getTag());
                Intent i = new Intent(context, ProfilDokterActivity.class);
                i.putExtra("username", id);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dokterList.size();
    }

    public List<Dokter> getList() {
        return dokterList;
    }

    public void addDokter(Dokter dokter) {
        dokterList.add(dokter);
    }
}
