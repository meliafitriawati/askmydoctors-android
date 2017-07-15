package com.askmydoctors.askmydoctors.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.DokterAdapter;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.utils.ServiceClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilDokterActivity extends AppCompatActivity {

    @InjectView(R.id.ivNamaDokter)
    TextView ivNamaDokter;
    @InjectView(R.id.ivSpesialisasi)
    TextView ivSpesialisasi;
    @InjectView(R.id.Tentang)
    TextView Tentang;
    @InjectView(R.id.pendidikan)
    TextView pendidikan;
    @InjectView(R.id.klinik)
    TextView klinik;
    @InjectView(R.id.alamat)
    TextView alamat;
    @InjectView(R.id.jadwal)
    TextView jadwal;
    @InjectView(R.id.ivGambarProfilDokter)
    CircleImageView ivGambarProfil;
    @InjectView(R.id.activity_profil_dokter)
    ScrollView activityProfilDokter;
    @InjectView(R.id.btnChat)
    RelativeLayout btnChat;


    private DokterAdapter dokterAdapter;
    private ProgressDialog pDialog;
    String username, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_dokter);
        ButterKnife.inject(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Intent i = getIntent();
        if (i.getExtras() != null) {
            username = i.getStringExtra("username");
            url = Config.URL + "dokter/getDetailDokter/" + username;
        }

        RequestParams params = new RequestParams();
        params.put("example", "ex");

        ServiceClient.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        JSONArray array = response.getJSONArray("dokter");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject dokter = array.getJSONObject(i);
                            ivNamaDokter.setText("Dokter. " + dokter.getString("fullname"));
                            ivSpesialisasi.setText("Spesialis " + dokter.getString("nama_spesialisasi"));
                            Tentang.setText(dokter.getString("tentang"));
                            pendidikan.setText(dokter.getString("pendidikan"));
                            klinik.setText(dokter.getString("nama_klinik"));
                            alamat.setText(dokter.getString("lokasi"));

                            String img = dokter.getString("image");
                            String url_img = Config.URL_WEB + "assets/img/dokter/" + img;
                            Log.d("url_img", url_img);

                            Picasso.with(ProfilDokterActivity.this)
                                    .load(url_img)
                                    .into(ivGambarProfil);
                        }
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.isEmpty()){
                    Intent i = new Intent(ProfilDokterActivity.this, ChatDetailActivity.class);
                    i.putExtra("chatWith", username);
                    startActivity(i);
                }
            }
        });

        String kode = Config.getString(ProfilDokterActivity.this, "kode");
        if (kode.equals("2")){
            btnChat.setVisibility(View.GONE);
        }

    }
}
