package com.example.newsaapppro.fields;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.newsaapppro.R;

public class business_content extends AppCompatActivity {
    WebView webView;
    Toolbar toolbar;
    ProgressBar progressBar;
    String murl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_content);
        progressBar = findViewById(R.id.b_detailed_progress);
        webView = findViewById(R.id.b_web_view);
        toolbar = findViewById(R.id.b_detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        murl = intent.getStringExtra("Url");


        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(murl);
        progressBar.setVisibility(View.GONE);

    }

}
