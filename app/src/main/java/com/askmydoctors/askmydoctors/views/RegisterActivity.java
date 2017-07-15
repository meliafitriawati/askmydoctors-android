package com.askmydoctors.askmydoctors.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner JK;
    EditText etUsername, etFullname, etEmail, etPassword, etCPassword;
    Button btnRegister;
    String jenisKelamin, username, fullname, email, password, cPassrowd;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        JK = (Spinner) findViewById(R.id.jenisKelaminSpinner);
        etUsername = (EditText) findViewById(R.id.usernamePengguna);
        etFullname = (EditText) findViewById(R.id.namaPengguna);
        etEmail = (EditText) findViewById(R.id.emailPenggunaDaftar);
        etPassword = (EditText) findViewById(R.id.passwordPenggunaDaftar);
        etCPassword = (EditText) findViewById(R.id.cPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        final List<String> jenis = new ArrayList<String>();
        jenis.add("Laki-laki");
        jenis.add("Perempuan");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, jenis);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        JK.setAdapter(dataAdapter);

        JK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                String jk=JK.getSelectedItem().toString();
                jenisKelamin = jk;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                cPassrowd = etCPassword.getText().toString().trim();
                fullname = etFullname.getText().toString().trim();
                
//                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || cPassrowd.isEmpty() || fullname.isEmpty() || jenisKelamin.isEmpty()){
//                    Toast.makeText(RegisterActivity.this, "Lengkapi Data Anda", Toast.LENGTH_SHORT).show();
//                }

                if (username.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Masukan username anda", Toast.LENGTH_SHORT).show();
                }else if (email.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Masukan email anda", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Masukan password anda", Toast.LENGTH_SHORT).show();
                }else if (cPassrowd.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Masukan konfirmasi password anda", Toast.LENGTH_SHORT).show();
                }else if (fullname.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Masukan nama lengkap anda", Toast.LENGTH_SHORT).show();
                }else if (jenisKelamin.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Pilih jenis kelamin anda", Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(cPassrowd)){
                        doRegister();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void doRegister() {
        pDialog.setMessage("Register ...");
        showDialog();

        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("username", username);
        params.put("password", password);
        params.put("fullname", fullname);
        params.put("gender", jenisKelamin);

        String url = Config.URL + "user/register";

        ServiceClient.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        hideDialog();
                        JSONObject user = response.getJSONObject("user");

                        Config.SetString(RegisterActivity.this, "username", user.getString("username"));
                        Config.SetString(RegisterActivity.this, "kode", user.getString("hak_akses"));

                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(i);
                    } else if (response.getInt("status") == 2){
                        hideDialog();
                        Toast.makeText(RegisterActivity.this, "Username telah digunakan", Toast.LENGTH_SHORT).show();
                    } else if (response.getInt("status") == 3){
                        hideDialog();
                        Toast.makeText(RegisterActivity.this, "Email telah digunakan", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                hideDialog();
                Toast.makeText(RegisterActivity.this, responseString , Toast.LENGTH_SHORT).show();
                Log.d("response", responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                hideDialog();
                Toast.makeText(RegisterActivity.this, "Can't reach server", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //jenisKelamin = item;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
