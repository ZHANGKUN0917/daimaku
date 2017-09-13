package com.example.hp.jinritoutiao.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hp.jinritoutiao.R;

public class VideoActivity extends AppCompatActivity {
    private WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        wv= ((WebView) findViewById(R.id.wv));

        Intent intent = getIntent();
        String path=intent.getStringExtra("path");
        wv.loadUrl(path);

        WebSettings settings = wv.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);

        wv.setWebChromeClient(new WebChromeClient());
        wv.setWebViewClient(new WebViewClient());

    }
}
