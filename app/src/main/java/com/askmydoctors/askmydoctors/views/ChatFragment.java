package com.askmydoctors.askmydoctors.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.ChatAdapter;
import com.askmydoctors.askmydoctors.models.Chat;
import com.askmydoctors.askmydoctors.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    private List<Chat> chatList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.chat_list);
        chatAdapter = new ChatAdapter(chatList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(chatAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        prepareChatData();

        return view;


    }

    private void prepareChatData() {
        Chat chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany...", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany...", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany...", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany...", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chat = new Chat("1", "Dr. Melia", "Dok saya Rida ingin bertany", "4/1/17", "image", "gigi");
        chatList.add(chat);

        chatAdapter.notifyDataSetChanged();
    }

}
