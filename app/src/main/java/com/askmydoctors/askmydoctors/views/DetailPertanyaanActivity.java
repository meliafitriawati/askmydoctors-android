package com.askmydoctors.askmydoctors.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.adapters.KomentarPAdapter;
import com.askmydoctors.askmydoctors.models.Komentar;
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

public class DetailPertanyaanActivity extends AppCompatActivity {
    String id_pertanyaan, url, urlkomentar;
    @InjectView(R.id.ivJudulPertanyaan)
    TextView ivJudulPertanyaan;
    @InjectView(R.id.nama)
    TextView nama;
    @InjectView(R.id.tanggal_views)
    TextView tanggalViews;
    @InjectView(R.id.ivDetilPertanyaan)
    TextView ivDetilPertanyaan;
    @InjectView(R.id.etKomentar)
    EditText etKomentar;
    @InjectView(R.id.KomentarPertanyaan)
    ListView KomentarPertanyaan;
    @InjectView(R.id.ivGambarProfilPer)
    CircleImageView ivGambarProfil;

    private KomentarPAdapter komentarPAdapter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pertanyaan);
        ButterKnife.inject(this);
        Button btnKomentar = (Button) findViewById(R.id.btnKomentar);
        Intent i = getIntent();

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        if (i.getExtras() != null) {
            id_pertanyaan = i.getStringExtra("id_pertanyaan");
            url = Config.URL + "diskusi/getDetailPertanyaan/" + id_pertanyaan;
            urlkomentar = Config.URL + "diskusi/getKomentar/" + id_pertanyaan;
        }

        findViewById(R.id.activity_detail_pertanyaan).requestFocus();

        RequestParams params = new RequestParams();
        params.put("example", "ex");
        ServiceClient.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        JSONArray array = response.getJSONArray("detail");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject detail = array.getJSONObject(i);
                            ivJudulPertanyaan.setText(detail.getString("judul"));
                            ivDetilPertanyaan.setText(detail.getString("pertanyaan"));
                            nama.setText(detail.getString("pengirim"));
                            tanggalViews.setText(detail.getString("waktu_kirim") + " | " + detail.getString("views"));

                            String img = detail.getString("img_user");
                            String url_img = Config.URL_WEB + "assets/img/user/" + img;
                            Log.d("url_img", url_img);

                            Picasso.with(DetailPertanyaanActivity.this)
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

        komentarPAdapter = new KomentarPAdapter(this);

        RequestParams param = new RequestParams();
        param.put("example", "ex");
        ServiceClient.get(urlkomentar, param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        JSONArray commentArray = response.getJSONArray("komentar");
                        komentarPAdapter.getList().clear();
                        for (int i = 0; i < commentArray.length(); i++) {
                            komentarPAdapter.addKomentar(Komentar.fromJSONData(commentArray.getJSONObject(i)));
                        }
                        komentarPAdapter.notifyDataSetChanged();
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        KomentarPertanyaan.setAdapter(komentarPAdapter);

        btnKomentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputKomentar = etKomentar.getText().toString().trim();
                if (inputKomentar.isEmpty()) {
                    Toast.makeText(DetailPertanyaanActivity.this, "Masukkan Komentar Anda", Toast.LENGTH_SHORT).show();
                } else {
                    TambahKomentar(inputKomentar);
                }
            }
        });
    }

    private void TambahKomentar(String inputKomentar) {
        pDialog.setMessage("Kirim ...");
        showDialog();

        RequestParams params = new RequestParams();
        params.put("id_pertanyaan", id_pertanyaan);
        params.put("pengirim", Config.GetString(DetailPertanyaanActivity.this, "username"));
        params.put("komentar", inputKomentar);
        params.put("kode_pengirim", Config.GetString(DetailPertanyaanActivity.this, "kode"));

        String url = Config.URL + "diskusi/tambahKomentar";

        ServiceClient.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        etKomentar.setText("");
                        hideDialog();
                        JSONArray commentArray = response.getJSONArray("komentar");
                        komentarPAdapter.getList().clear();
                        for (int i = 0; i < commentArray.length(); i++) {
                            komentarPAdapter.addKomentar(Komentar.fromJSONData(commentArray.getJSONObject(i)));
                        }
                        komentarPAdapter.notifyDataSetChanged();
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
