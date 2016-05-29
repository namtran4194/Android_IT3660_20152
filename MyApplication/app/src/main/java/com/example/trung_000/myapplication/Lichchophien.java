package com.example.trung_000.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Lichchophien extends AppCompatActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichchophien);
        web=(WebView)findViewById(R.id.web);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        startWebView("https://cungphuot.info/lich-hop-cho-phien-vung-cao-post4970.cp");
    }
    private void startWebView(String url){
        web.setWebViewClient(new WebViewClient(){
            ProgressDialog progressDialog;
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if(progressDialog==null){
                    progressDialog=new ProgressDialog(Lichchophien.this);
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
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
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
