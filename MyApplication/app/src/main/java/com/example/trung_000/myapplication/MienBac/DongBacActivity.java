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
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

import java.util.ArrayList;

public class DongBacActivity extends ActionBarActivity {
    ListView lv;
    String []title={"Kinh nghiệm phượt Hà Giang","Kinh nghiệm phượt Mẫu Sơn","Kinh nghiệm phượt Đồng Cao","Kinh nghiệm phượt Hồ Ba Bể","Kinh nghiệm phượt Thác Bản Giốc",
                    "Kinh nghiệm phượt Quan Lạn","Kinh nghiệm phượt Vịnh Hạ Long","Kinh nghiệm phượt Cô Tô","Kinh nghiệm phượt Thái Nguyên",
                    "Kinh nghiệm phượt Tuyên Quang","Kinh nghiệm phượt Phú Thọ"};
    int []detail={R.string.hagiang,R.string.mauson,R.string.dongcao,R.string.hobabe,R.string.thacbangioc,R.string.quanlan,R.string.vinhhalong,R.string.coto,R.string.thainguyen,R.string.tuyenquang,R.string.phutho};
    String []link={"https://cungphuot.info/wp-content/uploads/2015/12/ha-giang.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/mau-son.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/dong-cao.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/ho-ba-be.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/thac-ban-gioc.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/quan-lan.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/ha-long2.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/co-to.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/bus-thai-nguyen.png",
                    "http://tuyenquang.gov.vn:2222/Image/image/BQLDLNH/DSC02060.JPG",
                    "https://cungphuot.info/wp-content/uploads/2013/09/khach-san-nha-nghi-tai-phu-tho.jpg"};
    ArrayList<String> arrayListLinks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dong_bac);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        lv=(ListView)findViewById(R.id.lvdongbac);
        DongBacAdapter adapter=new DongBacAdapter(this,R.layout.lvtaybac,title,detail,link);
        AlphaInAnimationAdapter animationAdapter=new AlphaInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        arrayListLinks.add("http://hanhtrangphuot.tk/hagiang.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/mauson.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/dongcao.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hobabe.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/thacbangioc.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/quanlai.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/halong.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/coto.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/thainguyen.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/tuyenquang.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/phutho.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DongBacActivity.this,"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DongBacActivity.this, Taybac.class);
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
