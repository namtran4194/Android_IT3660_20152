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

import com.example.trung_000.myapplication.MienNam.DNBAdapter;
import com.example.trung_000.myapplication.R;
import com.example.trung_000.myapplication.Taybac;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;

import java.util.ArrayList;

public class BacTrungBoActivity extends ActionBarActivity {
    ListView lv;
    String []title={"Kinh nghiệm phượt Thanh Hóa","Kinh nghiệm phượt Phù Luông","Kinh nghiệm phượt Nghệ An","Kinh nghiệm  phượt Hà Tĩnh","Kinh nghiệm phượt Quảng Bình","Kinh nghiệm phượt Quảng Trị","Kinh nghiệm phượt Thừa Thiên Huế"};
    String []link={"https://cungphuot.info/wp-content/uploads/2014/07/bus-thanh-hoa.jpg",
            "http://media.baotintuc.vn/2015/02/22/07/36/phuluong.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-nghe-an.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-ha-tinh.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/07/bus-quang-binh.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-quang-tri2.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/08/phuot-hue.jpg"};
    int[] detail={R.string.thanhhoa,R.string.puluong,R.string.nghean,R.string.hatinh,R.string.quangbinh,R.string.quangtri,R.string.hue};
    ArrayList<String> arrayListLinks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_trung_bo);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        lv=(ListView)findViewById(R.id.lvbactrungbo);
        BacTrungBoAdapter adapter=new BacTrungBoAdapter(this,R.layout.lv_weather,title,detail,link);
        SwingLeftInAnimationAdapter animationAdapter=new SwingLeftInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        arrayListLinks.add("http://hanhtrangphuot.tk/thanhhoa.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/phuluong.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/nghean.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hatinh.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/quangbinh.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/quangtri.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hue.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BacTrungBoActivity.this,"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BacTrungBoActivity.this, Taybac.class);
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
