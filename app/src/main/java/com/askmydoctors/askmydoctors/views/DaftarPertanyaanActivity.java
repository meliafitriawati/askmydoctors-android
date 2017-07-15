package com.askmydoctors.askmydoctors.views;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.ChatAdapter;
import com.askmydoctors.askmydoctors.adapters.PertanyaanAdapter;
import com.askmydoctors.askmydoctors.models.Chat;
import com.askmydoctors.askmydoctors.models.Pertanyaan;
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

public class DaftarPertanyaanActivity extends AppCompatActivity {
    private List<Pertanyaan> pertanyaanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PertanyaanAdapter pertanyaanAdapter;
    String id_spesialisasi, url;
    FloatingActionButton fab;
    TextView noQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pertanyaan);

        Intent i = getIntent();
        if (i.getExtras() != null){
            id_spesialisasi = i.getStringExtra("id_spesialisasi");
            url = Config.URL + "diskusi/getPertanyaan/" + id_spesialisasi;
        }

        noQuestion = (TextView) findViewById(R.id.noQuestion);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        String kode = Config.GetString(DaftarPertanyaanActivity.this,"kode");
        if (kode.equals("2")){
            fab.setVisibility(View.GONE);
        }

        recyclerView = (RecyclerView) findViewById(R.id.daftarPertanyaan);
        pertanyaanAdapter = new PertanyaanAdapter(pertanyaanList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RequestParams params = new RequestParams();
        params.put("example", "ex");
        ServiceClient.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        JSONArray array = response.getJSONArray("pertanyaan");
                        pertanyaanAdapter.getList().clear();
                        for (int i = 0; i < array.length(); i++) {
                            pertanyaanAdapter.addPertanyaan(Pertanyaan.fromJSONData(array.getJSONObject(i)));
                        }
                        pertanyaanAdapter.notifyDataSetChanged();
                    } else {
                        noQuestion.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DaftarPertanyaanActivity.this, TambahPertanyaan.class);
                i.putExtra("id_spesialisasi", id_spesialisasi);
                startActivity(i);
            }
        });

        recyclerView.setAdapter(pertanyaanAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

}
