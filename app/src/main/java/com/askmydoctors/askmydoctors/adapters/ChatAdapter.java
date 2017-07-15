package com.askmydoctors.askmydoctors.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.models.Chat;
import com.askmydoctors.askmydoctors.models.Dokter;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.views.ChatDetailActivity;
import com.askmydoctors.askmydoctors.views.DetailArtikel;
import com.askmydoctors.askmydoctors.views.ProfilDokterActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>  {
    private List<Chat> chatList;
    private Context context;
    String kode,username;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama, tanggal;
        RelativeLayout holderChat;
        ImageView profil;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            profil = (ImageView) view.findViewById(R.id.ivGambarChat);
            nama = (TextView) view.findViewById(R.id.nama);
            tanggal = (TextView) view.findViewById(R.id.tanggal);
            holderChat = (RelativeLayout) view.findViewById(R.id.holderChat);
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

            kode = Config.getString(context, "kode");
            username = Config.getString(context, "username");

            if (kode.equals("2")){
                if (chat.getPenerima().equals(username)) {
                    holder.holderChat.setTag(chat.getPengirim());
                    holder.nama.setText(chat.getPengirim());
                    //holder.tanggal.setText(chat.getTanggal());

                    holder.holderChat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String user = String.valueOf(v.getTag());

                            Intent i = new Intent(context, ChatDetailActivity.class);
                            i.putExtra("chatWith", user);
                            context.startActivity(i);
                        }
                    });
                }
            }else if (kode.equals("3")){
                holder.holderChat.setTag(chat.getPenerima());
                holder.nama.setText(chat.getPenerima());
                String url_img = Config.URL_WEB + "assets/img/dokter/dokter_woman.png";

                Picasso.with(context)
                        .load(url_img)
                        .into(holder.profil);

                //holder.tanggal.setText(chat.getTanggal());

                holder.holderChat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = String.valueOf(v.getTag());

                        Intent i = new Intent(context, ChatDetailActivity.class);
                        i.putExtra("chatWith", user);
                        context.startActivity(i);
                    }
                });
            }


        }

        @Override
        public int getItemCount() {
            return chatList.size();
        }


    public List<Chat> getList() {
        return chatList;
    }

    public void addChat(Chat chat) {
        chatList.add(chat);
    }
}
