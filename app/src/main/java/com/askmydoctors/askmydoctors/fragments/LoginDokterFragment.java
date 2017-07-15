package com.askmydoctors.askmydoctors.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.utils.ServiceClient;
import com.askmydoctors.askmydoctors.views.MainActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginDokterFragment extends Fragment {
    EditText username, password;
    String inputUsername, inputPassword;
    Button btnLogin;
    private ProgressDialog pDialog;

    public LoginDokterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_dokter, container, false);
        username = (EditText) view.findViewById(R.id.usernameDokter);
        password = (EditText) view.findViewById(R.id.passwordDokter);
        btnLogin = (Button) view.findViewById(R.id.btnLoginDokter);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputUsername = username.getText().toString().trim();
                inputPassword = password.getText().toString().trim();
                if (inputUsername.isEmpty())
                    Toast.makeText(getActivity(), "Masukkan Username Anda", Toast.LENGTH_SHORT).show();
                else if (inputPassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Masukkan Password Anda", Toast.LENGTH_SHORT).show();
                }
                else{
                    doLogin(inputUsername, inputPassword);
                }

            }
        });

        return view;
    }

    private void doLogin(String inputUsername, String inputPassword) {
        pDialog.setMessage("Logging in ...");
        showDialog();
        String token = FirebaseInstanceId.getInstance().getToken();

        RequestParams params = new RequestParams();
        params.put("username", inputUsername);
        params.put("password", inputPassword);
        params.put("token", token);

        String url = Config.URL + "user/login_dokter";

        ServiceClient.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        hideDialog();
                        JSONObject user = response.getJSONObject("dokter");

                        Config.SetString(getActivity(), "username", user.getString("username"));
                        Config.SetString(getActivity(), "kode", user.getString("hak_akses"));

                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    } else {
                        hideDialog();
                        Toast.makeText(getActivity(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                hideDialog();
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                hideDialog();
                Toast.makeText(getActivity(), "Can't reach server", Toast.LENGTH_SHORT).show();
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
