package com.example.trung_000.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.trung_000.myapplication.TinTuc.NewsFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

//    ArrayList<Weather> arrayList = new ArrayList<Weather>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        imgvtaybac=(ImageView)findViewById(R.id.taybac);
//        imgvtaybac.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Toast.makeText(MainActivity.this,"Bạn chọn Tây Bắc",Toast.LENGTH_SHORT).show();
//            }
//        });
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container,new NewsFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Tin tức");
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.id_news:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new NewsFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Tin tức");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.id_trongnuoc:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new TrongnuocFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Điểm đến trong nước");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.id_nuocngoai:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new NuocngoaiFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Điểm đến nước ngoài");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.id_tienich:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new TienichFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Tiện ích");
//                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.id_about:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new FragmentAbout());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Thông Tin");

//                      item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.id_kinhnghiem:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new ExpFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Kinh Nghiệm Phượt");
//                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
//                    case R.id.id_exit:
//                        Toast.makeText(MainActivity.this,"Nhấn nút <- để thoát",Toast.LENGTH_SHORT).show();
//                        drawerLayout.closeDrawers();


                }
                return false;
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    Boolean click=false;
    @Override
    public void onBackPressed() {
        if(click==true){
            finish();
            System.exit(0);
        }
        click=true;
        Toast.makeText(MainActivity.this,"Nhấn ← một lần nữa để thoát",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                click=false;
            }
        },3000);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



}
