package com.example.trung_000.myapplication.MienBac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.trung_000.myapplication.R;
import com.example.trung_000.myapplication.Taybac;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;

import java.util.ArrayList;

public class TayBacActivity extends ActionBarActivity {
    ListView lvtaybac;

    String []title={"Kinh nghiệm phượt Bắc Hà","Kinh nghiệm phượt Mộc Châu","Kinh nghiệm  phượt Y Tý","Kinh nghiệm phượt Sa Pa",
                    "Kinh nghiệm phượt Thung Nai","Kinh nghiệm phượt Mai Châu","Kinh nghiệm phượt A Pa Chải",
                    "Kinh nghiệm phượt Lai Châu","Kịnh nghiệm phượt Tà Xùa"};
    String []link={"https://cungphuot.info/wp-content/uploads/2014/08/bac-ha.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/moc-chau.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/y-ty.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/sapa.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/thung-nai.jpg",
                     "https://cungphuot.info/wp-content/uploads/2014/08/mai-chau.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/08/apachai.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/kinh-nghiem-du-lich-phuot-lai-chau.png",
                    "http://kenhdulich.org/container/2015/content/articles/T-12/kinh-nghiem-va-lich-trinh-san-may-ta-xua/kinh-nghiem-va-lich-trinh-san-may-ta-xua-7.jpg"
                    };
//    int []img={R.drawable.bacha,R.drawable.mocchau,R.drawable.yty,R.drawable.sapa,R.drawable.thungnai,R.drawable.maichau,R.drawable.apachai};
    int[] detail={R.string.bacha,R.string.mocchau,R.string.yty,R.string.sapa,R.string.thungnai,R.string.maichau,R.string.apachai,R.string.laichau,R.string.taxua};
    ArrayList<String> arrayListLinks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tay_bac);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
//        ab.setDisplayUseLogoEnabled(true);
//        ab.setIcon(R.mipmap.ic_launcher);
//        toolbar=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        lvtaybac=(ListView)findViewById(R.id.lvtaybac);
        TayBacAdapter adapter=new TayBacAdapter(this,R.layout.lvtaybac,link,title,detail);
        ScaleInAnimationAdapter animationAdapter=new ScaleInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lvtaybac);
        lvtaybac.setAdapter(animationAdapter);
        LayoutInflater inflater=this.getLayoutInflater();
        View header=(View)inflater.inflate(R.layout.taybacheader,null,false);
        lvtaybac.addHeaderView(header);
        arrayListLinks.add("http://hanhtrangphuot.tk/bacha.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/mocchau.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/yty.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/sapa.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/thungnai.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/maichau.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/apachai.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/laichau.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/taxua.html");
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TayBacActivity.this,"Bạn chọn phượt Mù Căng Chải",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(TayBacActivity.this,Taybac.class);
                String link="http://hanhtrangphuot.tk/mucangchai.html";
                intent.putExtra("links",link);
                startActivity(intent);

            }
        });
        lvtaybac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TayBacActivity.this,"Bạn chọn"+" "+title[position - 1],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TayBacActivity.this, Taybac.class);
                String link = arrayListLinks.get(position - 1);
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

