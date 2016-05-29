package com.example.trung_000.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Taybac extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taybac);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        webView=(WebView)findViewById(R.id.web);

        Intent callerIntent=getIntent();
        String links = callerIntent.getStringExtra("links");
        Log.d("LINKS", ""+ links);
        startWebView(links);
    }
    private void startWebView(String url){
        webView.setWebViewClient(new WebViewClient(){
            ProgressDialog progressDialog;
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if(progressDialog==null){
                    progressDialog=new ProgressDialog(Taybac.this);
                    progressDialog.setMessage("Đang tải chờ xíu ...");
                    progressDialog.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(1);
        webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

}
