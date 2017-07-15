package com.askmydoctors.askmydoctors.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.ArtikelAdapter;
import com.askmydoctors.askmydoctors.adapters.ChatAdapter;
import com.askmydoctors.askmydoctors.models.Artikel;
import com.askmydoctors.askmydoctors.models.Chat;
import com.askmydoctors.askmydoctors.models.Spesialisasi;
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
public class ArtikelFragment extends Fragment {
    private List<Artikel> artikelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ArtikelAdapter artikelAdapter;
    String url_artikel = Config.URL + "artikel/getArtikel";
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public ArtikelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.artikel_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh2);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewArtikel();
            }
        });

        viewArtikel();

        return view;
    }

    private void viewArtikel() {
        artikelAdapter = new ArtikelAdapter(artikelList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        RequestParams params = new RequestParams();
        params.put("example", "ex");
        //Log.d("URL",String.valueOf(url_kategori));
        ServiceClient.get(url_artikel, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        JSONArray artikelArray = response.getJSONArray("artikel");
                        artikelAdapter.getList().clear();
                        for (int i = 0; i < artikelArray.length(); i++) {
                            artikelAdapter.addArtikel(Artikel.fromJSONData(artikelArray.getJSONObject(i)));
                        }
                        artikelAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), response.getString("sukses"), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(artikelAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
