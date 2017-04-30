package com.askmydoctors.askmydoctors.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.models.Chat;

import java.util.List;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>  {
    private List<Chat> chatList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama, pesan, tanggal;

        public MyViewHolder(View view) {
            super(view);
            nama = (TextView) view.findViewById(R.id.nama);
            pesan = (TextView) view.findViewById(R.id.pesan);
            tanggal = (TextView) view.findViewById(R.id.tanggal);
        }

    }

    public ChatAdapter(List<Chat> chatList) {
        this.chatList = chatList;
    }

    @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Chat chat = chatList.get(position);
            holder.nama.setText(chat.getPengirim());
            holder.pesan.setText(chat.getDetail());
            holder.tanggal.setText(chat.getTanggal());
        }

        @Override
        public int getItemCount() {
            return chatList.size();
        }
}
