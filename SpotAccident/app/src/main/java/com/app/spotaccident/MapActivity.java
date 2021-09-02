package com.app.spotaccident;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.app.spotaccident.model.TypePojo;

import java.io.IOException;
import java.io.InputStream;

public class MapActivity extends AppCompatActivity {

    private String TAG = MapActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        init();
    }

    public void init() {
        Intent intent = getIntent();
        TypePojo typePojo = (TypePojo) intent.getSerializableExtra("data");

        getSupportActionBar().setTitle(typePojo.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView webView = findViewById(R.id.webView);

        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String html = loadFromAsset(typePojo.getPath());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebChromeClient(new WebChromeClient());

        Log.e(TAG, "init: " + html);
        webView.loadDataWithBaseURL(null, html, mimeType, encoding, null);
    }

    public String loadFromAsset(String path) {
        String data = null;
        try {
            InputStream inputStream = getBaseContext().getAssets().open(path + ".html");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            data = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}