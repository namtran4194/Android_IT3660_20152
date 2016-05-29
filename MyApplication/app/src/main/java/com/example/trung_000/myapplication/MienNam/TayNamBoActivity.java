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
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

import java.util.ArrayList;

public class TayNamBoActivity extends AppCompatActivity {
    ListView lv;
    String []title={"Kinh nghiệm du lịch phượt Phú Quốc","Kinh nghiệm du lịch phượt đảo Nam Du","Kinh nghiệm du lịch phượt An Giang",
                    "Kinh nghiệm du lịch phượt Bạc Liêu","Kinh nghiệm du lịch phượt Bến Tre","Kinh nghiệm du lịch phượt Cà Mau",
                    "Kinh nghiệm du lịch phượt Cần Thơ","Kinh nghiệm du lịch phượt Đồng Tháp","Kinh nghiệm du lịch phượt Hậu Giang",
                    "Kinh nghiệm du lịch Long An","Kinh nghiệm du lịch phượt Sóc Trăng","Kinh nghiệm du lịch phượt Tiền Giang","Kinh nghiệm du lịch phượt Trà Vinh",
                    "Kinh nghiệm du lịch phượt Vĩnh Long"};
    String []link={"https://cungphuot.info/wp-content/uploads/2014/08/phu-quoc1.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/nam-du1.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-an-giang.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-bac-lieu.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-ben-tre.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-ca-mau.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-can-tho.jpg",
                    "https://cungphuot.info/wp-content/uploads/2013/12/khach-san-nha-nghi-tai-dong-thap.jpg",
                    "https://cungphuot.info/wp-content/uploads/2013/12/khach-san-nha-nghi-tai-hau-giang.jpg",
                    "https://cungphuot.info/wp-content/uploads/2013/11/khach-san-tai-long-an.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-soc-trang1.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-tien-giang2.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/khach-san-nha-nghi-tai-tra-vinh2.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/bus-vinh-long.jpg"};
    int []detail={R.string.phuquoc,R.string.namdu,R.string.angiang,R.string.baclieu,R.string.bentre,R.string.camau,R.string.cantho,
                    R.string.dongthap,R.string.haugiang,R.string.longan,R.string.soctrang,R.string.tiengiang,R.string.travinh,R.string.vinhlong};
    ArrayList<String> arrayListLinks=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tay_nam_bo);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        lv=(ListView)findViewById(R.id.lvtnb);
        TNBAdapter adapter=new TNBAdapter(this,R.layout.lvtaybac,title,detail,link);
        SwingRightInAnimationAdapter animationAdapter=new SwingRightInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        arrayListLinks.add("http://hanhtrangphuot.tk/phuquoc.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/namdu.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/angiang.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/baclieu.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/bentre.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/camau.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/cantho.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/dongthap.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/haugiang.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/longan.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/soctrang.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/tiengiang.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/travinh.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/vinhlong.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TayNamBoActivity.this,"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TayNamBoActivity.this, Taybac.class);
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
