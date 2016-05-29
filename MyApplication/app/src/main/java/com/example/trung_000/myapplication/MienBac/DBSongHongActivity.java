package com.example.trung_000.myapplication.MienBac;

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
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;

import java.util.ArrayList;

public class DBSongHongActivity extends ActionBarActivity {
    ListView lv;
    String []title={"Kinh nghiệm du lịch phượt Tam Đảo","Kinh nghiệm du lịch phượt Cát Bà","Kinh nghiệm du lịch phượt Vịnh Lan Hạ ","Kinh nghiệm du lịch Nam Định",
                    "Kinh nghiệm du lịch phượt Bắc Ninh","Kinh nghiệm du lịch phượt Ninh Bình","Kinh nghiệm du lịch phượt Hải Dương",
                    "Kinh nghiệm du lịch phượt Hà Nam","Kinh nghiệm du lịch phượt Thái Bình","Kinh nghiệm du lịch phượt Hưng Yên",
                    "Kinh nghiệm du lịch phượt Hà Nội"};
    String []link={"https://cungphuot.info/wp-content/uploads/2014/08/tam-dao.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/cat-ba1.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/lan-ha.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/nam-dinh1.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/bac-ninh.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/bai-dinh.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-hai-duong.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-ha-nam.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-thai-binh1.jpg",
                    "https://cungphuot.info/wp-content/uploads/2013/12/khach-san-nha-nghi-tai-hung-yen.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/09/thue-xe-may-tai-ha-noi.jpg"};
    int []detail={R.string.tamdao,R.string.catba,R.string.vinhlanha,R.string.namdinh,R.string.bacninh,R.string.ninhbinh,
                    R.string.haiduong,R.string.hagiang,R.string.thaibinh,R.string.hungyen,R.string.hanoi};
    ArrayList<String> arrayListLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbsong_hong);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        lv=(ListView)findViewById(R.id.lvdbsh);
        DBSongHongAdapter adapter=new DBSongHongAdapter(this,R.layout.lvtaybac,title,detail,link);
        SwingLeftInAnimationAdapter animationAdapter=new SwingLeftInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        arrayListLinks.add("http://hanhtrangphuot.tk/tamdao.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/catba.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/vinhlanha.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/namdinh.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/bacninh.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/ninhbinh.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/haiduong.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hanam.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/thaibinh.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hungyen.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hanoi.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DBSongHongActivity.this,"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DBSongHongActivity.this, Taybac.class);
                String link = arrayListLinks.get(position);
                intent.putExtra("links", link);
                startActivity(intent);
            }
        });
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
