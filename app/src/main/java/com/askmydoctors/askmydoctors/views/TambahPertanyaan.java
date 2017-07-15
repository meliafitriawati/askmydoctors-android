package com.askmydoctors.askmydoctors.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.utils.ServiceClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TambahPertanyaan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    EditText judul, pertanyaan;
    String et_Judul, et_Pertanyaan, id_spesialisasi, id_privasi;
    Button btnTambah;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pertanyaan);

        Intent i = getIntent();
        if (i.getExtras() != null){
            id_spesialisasi = i.getStringExtra("id_spesialisasi");
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        judul = (EditText) findViewById(R.id.tp_Judul);
        pertanyaan = (EditText) findViewById(R.id.tp_Pertanyaan);
        btnTambah = (Button) findViewById(R.id.btnTambah);

        final List<String> categories = new ArrayList<String>();
        categories.add("Publik");
        categories.add("Hanya Saya");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        spinner.setOnItemSelectedListener(this);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_Judul = judul.getText().toString();
                et_Pertanyaan = pertanyaan.getText().toString();
                if (et_Judul.isEmpty())
                    Toast.makeText(TambahPertanyaan.this, "Masukkan Judul Anda", Toast.LENGTH_SHORT).show();
                else if (et_Pertanyaan.isEmpty()) {
                    Toast.makeText(TambahPertanyaan.this, "Masukkan Pertanyaan Anda", Toast.LENGTH_SHORT).show();
                }
                else{
                    tambahPertanyaan();
                }

            }
        });

    }

    private void tambahPertanyaan() {
        pDialog.setMessage("Kirim Pertanyaan ...");
        showDialog();

        RequestParams params = new RequestParams();
        params.put("judul", et_Judul);
        params.put("detail", et_Pertanyaan);
        params.put("privasi", id_privasi);
        params.put("id_spesialis", id_spesialisasi);
        params.put("pengirim", Config.GetString(this, "username"));
        params.put("status", "BELUM TERJAWAB");

        String url = Config.URL + "diskusi/tambahPertanyaan";
        ServiceClient.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        hideDialog();

                        String id_pertanyaan = response.getString("id_pertanyaan");

                        Intent i = new Intent(TambahPertanyaan.this, DetailPertanyaanActivity.class);
                        i.putExtra("id_pertanyaan", id_pertanyaan);
                        startActivity(i);
                    } else {
                        hideDialog();
                        Toast.makeText(TambahPertanyaan.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                hideDialog();
                Toast.makeText(TambahPertanyaan.this, responseString, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (item.equals("Publik")){
            id_privasi = "1";
        }else if (item.equals("Hanya Saya")){
            id_privasi = "2";
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
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
