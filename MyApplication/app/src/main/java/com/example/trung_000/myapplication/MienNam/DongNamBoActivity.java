package com.example.trung_000.myapplication.MienNam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class DongNamBoActivity extends AppCompatActivity {
    ListView lv;
    String []title={"Kinh nghiệm du lịch phượt Sài Gòn","Kinh nghiệm du lịch phượt Vũng Tàu","Kinh nghiệm du lịch phượt Bình Dương",
                    "Kinh nghiệm du lịch phượt Bình Phước","Kinh nghiệm du lịch phượt Đồng Nai","Kinh nghiệm du lịch phượt Tây Ninh"};
    int [] detail={R.string.saigon,R.string.vungtau,R.string.binhduong,R.string.binhphuoc,R.string.dongnai,R.string.tayninh};
    String[]link={"https://cungphuot.info/wp-content/uploads/2014/07/bus-sai-gon.jpg",
                  "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-ba-ria-vung-tau.jpg",
                  "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-binh-duong.jpg",
                  "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-binh-phuoc.jpg",
                  "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-dong-nai.jpg",
                  "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-tay-ninh2.jpg"};
    ArrayList<String> arrayListLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_nam_bo);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        lv=(ListView)findViewById(R.id.lvdnb);
        DNBAdapter adapter=new DNBAdapter(this,R.layout.customlv,title,detail,link);
        SwingLeftInAnimationAdapter animationAdapter=new SwingLeftInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);

        arrayListLinks.add("http://hanhtrangphuot.tk/saigon.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/vungtau.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/binhduong.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/binhphuoc.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/dongnai.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/tayninh.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DongNamBoActivity.this,"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DongNamBoActivity.this, Taybac.class);
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
