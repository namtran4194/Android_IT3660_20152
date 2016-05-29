package com.example.trung_000.myapplication.MienTrung;

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
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;

public class NamTrungBoActivity extends AppCompatActivity {
    ListView lv;
    String []title={"Kinh nghiệm du lịch Đà Nẵng","Kinh nghiệm du lịch phượt Hội An","Kinh nghiệm du lịch phượt Cù Lao Chàm",
            "Kinh nghiệm du lịch phượt Lý Sơn","Kinh nghiệm du lịch Quy Nhơn, Bình Định","Kinh nghiệm du lịch đảo Cực Đông",
            "Kinh nghiệm du lịch Nha Trang","Kinh nghiệm du lịch Bình Ba","Kinh nghiệm phượt Bình Hưng",
            "Kinh nghiệm du lịch đảo Mũi Né","Kinh nghiệm du lịch Phú Yên","Kinh nghiệm du lịch Ninh Thuận"
    };
    String []link={"https://cungphuot.info/wp-content/uploads/2014/08/da-nang.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/08/hoi-an1.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/08/cu-lao-cham.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/08/ly-son1.jpg",
            "https://cungphuot.info/wp-content/uploads/2016/01/du-lich-quy-nhon1.jpg",
            "https://cungphuot.info/wp-content/uploads/2016/01/cuc-dong1.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/08/nha-trang.jpg",
            "https://cungphuot.info/wp-content/uploads/2015/04/binh-ba.jpg",
            "https://cungphuot.info/wp-content/uploads/2015/04/binh-hung.jpg",
            "https://cungphuot.info/wp-content/uploads/2014/08/mui-ne.jpg",
            "https://cungphuot.info/wp-content/uploads/2013/09/khach-san-nha-nghi-tai-phu-yen.jpg",
            "https://cungphuot.info/wp-content/uploads/2013/09/khach-san-nha-nghi-tai-ninh-thuan.jpg"};
    int[] detail={R.string.danang,R.string.hoian,R.string.culaocham,R.string.lyson,R.string.quynhon,R.string.cucdong,
            R.string.nhatrang,R.string.daobinhba,R.string.daobinhhung,R.string.muine,R.string.phuyen,R.string.ninhthuan};
    ArrayList<String> arrayListLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nam_trung_bo);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        lv=(ListView)findViewById(R.id.lvntb);
        NTBAdapter adapter=new NTBAdapter(this,R.layout.lvtaybac,title,detail,link);
        SwingBottomInAnimationAdapter animationAdapter=new SwingBottomInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        arrayListLinks.add("http://hanhtrangphuot.tk/danang.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hoian.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/culaocham.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/lyson.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/quynhon.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/cucdong.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/nhatrang.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/binhba.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/binhhung.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/muine.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/phuyen.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/ninhthuan.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NamTrungBoActivity.this,"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NamTrungBoActivity.this, Taybac.class);
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
