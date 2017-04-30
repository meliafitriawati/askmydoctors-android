package com.askmydoctors.askmydoctors.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.askmydoctors.askmydoctors.R;
import com.askmydoctors.askmydoctors.utils.Config;

public class DetailArtikel extends AppCompatActivity {
    WebView artikel;
    String url, id_artikel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        Intent i = getIntent();
        if (i.getExtras() != null){
            id_artikel = i.getStringExtra("id_artikel");
            url = Config.URL_WEB + "artikel/m/" + id_artikel;
        }

        artikel = (WebView) findViewById(R.id.artikel);
        artikel.setWebViewClient(new MyBrowser());

        artikel.getSettings().setLoadsImagesAutomatically(true);
        artikel.getSettings().setJavaScriptEnabled(true);
        artikel.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        artikel.loadUrl(url);

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
