package com.askmydoctors.askmydoctors.views;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.utils.Config;
import com.askmydoctors.askmydoctors.utils.ServiceClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginUserFragment extends Fragment {
    EditText email, password;
    String inputEmail, inputPassword;
    Button btnLogin;
    private ProgressDialog pDialog;

    public LoginUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login_user, container, false);
        email = (EditText) view.findViewById(R.id.usernamePengguna);
        password = (EditText) view.findViewById(R.id.passwordPengguna);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputEmail = email.getText().toString().trim();
                inputPassword = password.getText().toString().trim();
                if (inputEmail.isEmpty())
                    Toast.makeText(getActivity(), "Masukkan Email Anda", Toast.LENGTH_SHORT).show();
                else if (inputPassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Masukkan Password Anda", Toast.LENGTH_SHORT).show();
                }
                else{
                    doLogin(inputEmail, inputPassword);
                }

            }
        });
        return view;
    }

    private void doLogin(String inputEmail, String inputPassword) {
        pDialog.setMessage("Logging in ...");
        showDialog();

        RequestParams params = new RequestParams();
        params.put("email", inputEmail);
        params.put("password", inputPassword);

        String url = Config.URL + "user/login_user";

        ServiceClient.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    if (response.getInt("status") == 1) {
                        hideDialog();
                        JSONObject user = response.getJSONObject("user");

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
