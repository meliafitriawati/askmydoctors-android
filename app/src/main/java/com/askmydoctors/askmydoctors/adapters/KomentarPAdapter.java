package com.askmydoctors.askmydoctors.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.models.Komentar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meliafitriawati on 4/27/2017.
 */

public class KomentarPAdapter extends BaseAdapter {
    Context context;
    List<Komentar> komentarList = new ArrayList<Komentar>();
    ViewHolder viewHolder;

    public KomentarPAdapter(Context context) {
        this.context = context;
    }

    public void addKomentar(Komentar kom) {
        komentarList.add(kom);
    }
    public List<Komentar> getList() {
        return this.komentarList;
    }
    @Override
    public int getCount() {
        return komentarList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            convertView = mInflater.inflate(R.layout.item_komentar_diskusi, null);
            viewHolder = new ViewHolder();
            viewHolder.nama = (TextView) convertView.findViewById(R.id.ivNamaPengguna);
            viewHolder.tanggal = (TextView) convertView.findViewById(R.id.ivTanggalKirim);
            viewHolder.komentar = (TextView) convertView.findViewById(R.id.ivKomentarPertanyaan);
        }
        viewHolder.nama.setText(komentarList.get(position).getPengirim());
        viewHolder.tanggal.setText(komentarList.get(position).getWaktu());
        viewHolder.komentar.setText(komentarList.get(position).getKomentar());

        return convertView;
    }
    public Context getContext() {
        return context;
    }

    private static class ViewHolder {
        TextView nama;
        TextView tanggal;
        TextView komentar;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
