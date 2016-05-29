package com.example.trung_000.myapplication.Weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trung_000.myapplication.R;
import com.squareup.picasso.Picasso;

public class WeatherDetail extends AppCompatActivity {
    TextView txtdiachi,txtmain,txtdescription,txtmax,
            txtmin,txtday,txtnight,txteve,txtmorn,txtmax1,txtmin1,txthumidity,txtspeed,txtngay;
    ImageView imgv,imgvbackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_weather_detail);
        imgv=(ImageView)findViewById(R.id.imgv);
        txtdiachi=(TextView)findViewById(R.id.txtdiachi);
        txtmain=(TextView)findViewById(R.id.txtmain);
        txtdescription=(TextView)findViewById(R.id.txtdescription);
        txtmax=(TextView)findViewById(R.id.txtmax);
        txtmin=(TextView)findViewById(R.id.txtmin);
        txtday=(TextView)findViewById(R.id.txtday);
        txtnight=(TextView)findViewById(R.id.txtnight);
        txteve=(TextView)findViewById(R.id.txteve);
        txtmorn=(TextView)findViewById(R.id.txtmorn);
        txtmax1=(TextView)findViewById(R.id.txtmax1);
        txtmin1=(TextView)findViewById(R.id.txtmin1);
        txthumidity=(TextView)findViewById(R.id.txthumidity);
        txtspeed=(TextView)findViewById(R.id.txtspeed);
        Intent callerIntent=getIntent();
        String links = callerIntent.getStringExtra("diachi");
        String main=callerIntent.getStringExtra("main");
        String description=callerIntent.getStringExtra("description");
        String max=callerIntent.getStringExtra("max");
        String min=callerIntent.getStringExtra("min");
        String day=callerIntent.getStringExtra("day");
        String night=callerIntent.getStringExtra("night");
        String eve=callerIntent.getStringExtra("eve");
        String morn=callerIntent.getStringExtra("morn");
        String humidity=callerIntent.getStringExtra("humidity");
        String speed=callerIntent.getStringExtra("speed");
        String icon=callerIntent.getStringExtra("iconid");
        txtmain.setText(main);
        txtdiachi.setText(links);
        txtdescription.setText(description);
        txtmax.setText("↑"+max +" °C" );
        txtmin.setText("↓"+min + " °C");
        txtday.setText("Day temperature :"+" "+day+" °C");
        txtnight.setText("Night temperature :"+" "+night+" °C");
        txteve.setText("Evening temperature :"+" "+eve+ "° C");
        txtmorn.setText("Morning temperature :"+" "+morn+ " °C");
        txthumidity.setText("Humidity :"+" "+humidity+ "%");
        txtspeed.setText("Wind speed :"+" "+speed + "km/h");
        txtmax1.setText("Max daily temperature :"+" "+max + " °C");
        txtmin1.setText("Min daily temperature :"+" "+min +" °C");
        Picasso.with(this).load("http://openweathermap.org/img/w/"+icon+".png").into(imgv);
        String ngay=callerIntent.getStringExtra("ngay");
        txtngay=(TextView)findViewById(R.id.txtngay);
        txtngay.setText("Ngày "+ " "+ngay);
//        imgvbackground=(ImageView)findViewById(R.id.imgvbackground);
//        if(main.equals("Rain")){
//            imgvbackground.setBackgroundResource(R.drawable.rain2);
//        }
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
