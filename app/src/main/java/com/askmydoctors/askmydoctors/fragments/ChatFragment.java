package com.askmydoctors.askmydoctors.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.ChatAdapter;
import com.askmydoctors.askmydoctors.models.Chat;
import com.askmydoctors.askmydoctors.models.Dokter;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.utils.DividerItemDecoration;
import com.askmydoctors.askmydoctors.utils.ServiceClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    private List<Chat> chatList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private TextView noUsersText;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.chat_list);
        noUsersText = (TextView) view.findViewById(R.id.noUsersText);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewChat();
            }
        });

        viewChat();

        return view;
    }

    private void viewChat() {

        chatAdapter = new ChatAdapter(chatList);

        String kode = Config.getString(getActivity(), "kode");
        String username = Config.getString(getActivity(), "username");

        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("kode", kode);
        String url = Config.URL + "chat/getAllChat";

        ServiceClient.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        JSONArray kategoriArray = response.getJSONArray("chat");
                        chatAdapter.getList().clear();
                        for (int i = 0; i < kategoriArray.length(); i++) {
                            chatAdapter.addChat(Chat.fromJSONData(kategoriArray.getJSONObject(i)));
                        }
                        chatAdapter.notifyDataSetChanged();

                        if (chatAdapter.getList().size() == 0 ){
                            noUsersText.setVisibility(View.VISIBLE);
                        }
                    } else {
                        noUsersText.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(chatAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
