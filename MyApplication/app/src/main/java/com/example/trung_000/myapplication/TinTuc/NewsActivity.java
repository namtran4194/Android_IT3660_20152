package com.example.trung_000.myapplication.TinTuc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.trung_000.myapplication.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class NewsActivity extends AppCompatActivity {
    WebView webView;
    String detail="";
    String links;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        webView=(WebView)findViewById(R.id.news);
//        webView.getSettings().setLoadWithOverviewMode(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        webView.setScrollbarFadingEnabled(false);
//        webView.getSettings().setBuiltInZoomControls(true);
        Intent callerIntent=getIntent();
         links = callerIntent.getStringExtra("links");
        Log.d("LINKS", ""+ links);
        new GetData().execute();


    }
    public class GetData extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog= new ProgressDialog(NewsActivity.this);
            progressDialog.setMessage("Đang tải chờ xíu ...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc=Jsoup.connect(links).get();
                Elements title = doc.select("div.title_news");
                Elements date=doc.select("div.block_timer.left.txt_666");
                Elements description=doc.select("div.short_intro.txt_666");
                Elements main=doc.select("div.fck_detail.width_common");
                Elements main1=doc.select("div.block_content_slide_showdetail");
                Elements author=doc.select("div.author_mail.width_common");
                detail += "<h3 style = \" color: #027000 \">" + title.text()
                        + "</h3>";
                detail += "<font size=\" 2em \" style = \" color: #666 \"><em>"
                        + date.text() + "</em></font>";
                detail += "<p style = \" color: #444 \"><b>" + "<font size=\" 4em \" >"
                        + description.text() + "</font></b></p>";
                detail+="<font size=\" 4em \" >"+ main1.toString() + "</font>";
                detail += "<font size=\" 4em \" >"+  main.toString() + "</font>";
                detail+="<p style=\"text-align: right;\" ><strong>"+author.toString()+"</strong></p>";
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            webView.loadDataWithBaseURL(
                    "",
                    "<style>img{display: inline;height: auto;max-width: 100%;}"
                            + " p {font-family:\"Tangerine\", \"Sans-serif\",  \"Serif\" font-size: 48px} </style>"
                            + detail, "text/html", "UTF-8", "");
        }
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
