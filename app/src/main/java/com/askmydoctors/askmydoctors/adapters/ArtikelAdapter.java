package com.askmydoctors.askmydoctors.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.models.Artikel;
import com.askmydoctors.askmydoctors.models.Chat;
import com.askmydoctors.askmydoctors.models.Pertanyaan;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.views.DetailArtikel;
import com.askmydoctors.askmydoctors.views.DetailPertanyaanActivity;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.util.List;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.MyViewHolder>  {
    private List<Artikel> artikelList;
    private Context artikelContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView judul;
        public ImageView foto_profil;

        public MyViewHolder(View view) {
            super(view);
            artikelContext = view.getContext();
            judul = (TextView) view.findViewById(R.id.judul_artikel);
            foto_profil = (ImageView) view.findViewById(R.id.foto_profil);
        }
    }

    public ArtikelAdapter(List<Artikel> artikelList) {
        this.artikelList = artikelList;
    }

    public ArtikelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artikel, parent, false);

        return new ArtikelAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArtikelAdapter.MyViewHolder holder, int position) {
        Artikel artikel = artikelList.get(position);
        holder.judul.setTag(artikel.getId());
        holder.judul.setText(artikel.getJudul());

        //http://localhost/askmydoctors/assets/img/artikel/buah apel.jpg
        String img = artikel.getImg();
        String url_img = Config.URL_WEB + "assets/img/artikel/" + img;

        Picasso.with(artikelContext)
                .load(url_img)
                .into(holder.foto_profil);

        holder.judul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_artikel = String.valueOf(v.getTag());
                Log.d("id_artikel", id_artikel);
                Intent i = new Intent(artikelContext, DetailArtikel.class);
                i.putExtra("id_artikel", id_artikel);
                artikelContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artikelList.size();
    }

    public List<Artikel> getList() {
        return artikelList;
    }

    public void addArtikel(Artikel artikel) {
        artikelList.add(artikel);
    }
}
