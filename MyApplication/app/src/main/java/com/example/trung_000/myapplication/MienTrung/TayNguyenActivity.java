package com.example.trung_000.myapplication.MienTrung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.trung_000.myapplication.R;
import com.example.trung_000.myapplication.Taybac;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

import java.util.ArrayList;

public class TayNguyenActivity extends ActionBarActivity {
    ListView lv;
    String []title={"Kinh nghiệm du lịch phượt Buôn Ma Thuột","Kinh nghiệm du lịch phượt Đắk Nông","Kinh nghiệm  du lịch phượt Gia Lai","Kinh nghiệm du lịch phượt Kom Tum","Kinh nghiệm du lịch phượt Đà Lạt"};
    String []link={"https://cungphuot.info/wp-content/uploads/2014/08/buon-ma-thuot1.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-dak-nong.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-gia-lai.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-o-kon-tum.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-o-lam-dong.jpg",
            };
    int[] detail={R.string.buonmathuot,R.string.daknong,R.string.gialai,R.string.komtum,R.string.lamdong};
    ArrayList<String> arrayListLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tay_nguyen);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        lv=(ListView)findViewById(R.id.lvtaynguyen);
        TayNguyenAdapter adapter=new TayNguyenAdapter(this,R.layout.lvtaybac,title,detail,link);
        SwingRightInAnimationAdapter animationAdapter=new SwingRightInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        arrayListLinks.add("http://hanhtrangphuot.tk/buonmathuot.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/daknong.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/gialai.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/kontum.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/dalat.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TayNguyenActivity.this,"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TayNguyenActivity.this, Taybac.class);
                String link = arrayListLinks.get(position);
                intent.putExtra("links", link);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
