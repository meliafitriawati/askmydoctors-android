package com.askmydoctors.askmydoctors.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.SpesialisasiAdapter;
import com.askmydoctors.askmydoctors.models.Spesialisasi;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.utils.DividerItemDecoration;
import com.askmydoctors.askmydoctors.utils.ServiceClient;
import com.loopj.android.http.AsyncHttpClient;
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
public class DiskusiFragment extends Fragment {
    private List<Spesialisasi> spesialisasiList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SpesialisasiAdapter spesialisasiAdapter;
    String url_kategori = Config.URL + "diskusi/getKategori";

    public DiskusiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diskusi, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.spesialisasi_list);
        spesialisasiAdapter = new SpesialisasiAdapter(spesialisasiList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RequestParams params = new RequestParams();
        params.put("example", "ex");
        Log.d("URL",String.valueOf(url_kategori));
        ServiceClient.get(url_kategori, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        JSONArray kategoriArray = response.getJSONArray("kategori");
                        spesialisasiAdapter.getList().clear();
                        for (int i = 0; i < kategoriArray.length(); i++) {
                            spesialisasiAdapter.addSpesialisasi(Spesialisasi.fromJSONData(kategoriArray.getJSONObject(i)));
                        }
                        spesialisasiAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), response.getString("sukses"), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        recyclerView.setAdapter(spesialisasiAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        return view;
    }


    private void prepareChatData() {
        Spesialisasi spesialisasi = new Spesialisasi("1", "Konsultasi Gigi");
        spesialisasiList.add(spesialisasi);

        spesialisasi = new Spesialisasi("1", "Konsultasi Gigi");
        spesialisasiList.add(spesialisasi);

        spesialisasi = new Spesialisasi("1", "Konsultasi Gigi");
        spesialisasiList.add(spesialisasi);

        spesialisasi = new Spesialisasi("1", "Konsultasi Gigi");
        spesialisasiList.add(spesialisasi);

        spesialisasi = new Spesialisasi("1", "Konsultasi Gigi");
        spesialisasiList.add(spesialisasi);


        spesialisasiAdapter.notifyDataSetChanged();

    }

}
