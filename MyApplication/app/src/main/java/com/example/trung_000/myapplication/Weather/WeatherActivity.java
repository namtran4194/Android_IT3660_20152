package com.example.trung_000.myapplication.Weather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trung_000.myapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtnhap;
    Button btnok;
    LinearLayout layoutnhap;
    TextView txtdiachi;
    ListView lv;
    String link = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
    ArrayList<Weather> list_weather;
    Weather weather;
    Adapter adapter;
    ProgressDialog pDialog;
    String name, country;
    NumberFormat format = new DecimalFormat("#0.0");
    ArrayList<String>listday=new ArrayList<String>();
    String linkcurrent="http://api.openweathermap.org/data/2.5/weather?q=";
    String main2;
    ImageView imgvcurrent,imgvhumidity,imgvspeed;
    TextView txttemp,txtmain,txtdescription,txthumidity,txtspeed,txttime,txtdubao,txtline;
    String temp,humidity,speed,description,icon;
    Double temp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        android.support.v7.app.ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        edtnhap=(EditText)findViewById(R.id.edtnhap);
        btnok=(Button)findViewById(R.id.btnok);
        layoutnhap=(LinearLayout)findViewById(R.id.layoutnhap);
        btnok.setOnClickListener(this);
        lv=(ListView)findViewById(R.id.lv);
        imgvcurrent=(ImageView)findViewById(R.id.imgvcurrent);
        txtdiachi=(TextView)findViewById(R.id.txtdiachi);
        txtmain=(TextView)findViewById(R.id.txtmain);
        txttemp=(TextView)findViewById(R.id.txttemp);
        txtdescription=(TextView)findViewById(R.id.txtdescription);
        txthumidity=(TextView)findViewById(R.id.txthumidity);
        txtspeed=(TextView)findViewById(R.id.txtwindspeed);
        txttime=(TextView)findViewById(R.id.txttime);
        imgvhumidity=(ImageView)findViewById(R.id.imgvhumidity);
        imgvspeed=(ImageView)findViewById(R.id.imgvspeed);
        txtdubao=(TextView)findViewById(R.id.txtdubao);
        txtline=(TextView)findViewById(R.id.txtline);
    }

    @Override
    public void onClick(View v) {
        String diachi;
        String linkmoi,linkmoi1;
        diachi=edtnhap.getText().toString();
        String diachimoi=diachi.replace(" ","%20");
        linkmoi=link+diachimoi+"&cnt=7&appid=88537f8bef5eeff0c4a99eb06406ab35";
        linkmoi1=linkcurrent+diachimoi+"&appid=88537f8bef5eeff0c4a99eb06406ab35";
        Log.d("link",linkmoi);
        GetData getData=new GetData();
        getData.execute(linkmoi,linkmoi1);


        list_weather =new ArrayList<Weather>();
        layoutnhap.setVisibility(View.GONE);
    }
    public class GetData extends AsyncTask<String, Void, ArrayList<Weather>> {
        StringBuilder dulieu,dulieu2;

        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(WeatherActivity.this);
            pDialog.setMessage("Đang tải chờ xíu ...");
            pDialog.setCancelable(false);
            pDialog.show();
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Weather> doInBackground(String... params) {
            try {
                URL url=new URL(params[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(reader);
                String dong="";
                dulieu=new StringBuilder();

                while ((dong=bufferedReader.readLine())!=null){
                    dulieu.append(dong);
                }
                bufferedReader.close();
                reader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d("dulieu",dulieu.toString());


            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            return dulieu.toString();
            try {
                JSONObject object=new JSONObject(dulieu.toString());
                JSONObject city=object.getJSONObject("city");
                name=city.getString("name");
                country=city.getString("country");
//                txtdiachi.setText(edtnhap.getText().toString()+","+city.getString("name")+","+city.getString("country"));
                JSONArray listday=object.getJSONArray("list");

                for (int i=0;i<listday.length();i++){
                    Weather weather=new Weather();
                    JSONObject daily=listday.getJSONObject(i);
                    JSONObject temp=daily.getJSONObject("temp");
                    String humidity="";
                    String speed="";
                    humidity=daily.getString("humidity");
                    speed=daily.getString("speed");
                    weather.setHumidity(humidity);
                    weather.setSpeed(speed);
                    String max="";
                    String min="";
                    String day="";
                    String night="";
                    String eve="";
                    String morn="";
                    day=temp.getString("day");
                    night=temp.getString("night");
                    eve=temp.getString("eve");
                    morn=temp.getString("morn");
                    max=temp.getString("max");
                    min=temp.getString("min");
                    Double max1,min1,day1,night1,eve1,morn1;
                    max1=Double.parseDouble(max)- 273.15;
                    min1=Double.parseDouble(min)-273.15;
                    day1 = Double.parseDouble(day) - 273.15;
                    eve1 = Double.parseDouble(eve) - 273.15;
                    night1 = Double.parseDouble(night) - 273.15;
                    morn1 = Double.parseDouble(morn) - 273.15;
                    weather.setMax(String.valueOf(format.format(max1)));
                    weather.setMin(String.valueOf(format.format(min1)));
                    weather.setDay(String.valueOf(format.format(day1)));
                    weather.setNigth(String.valueOf(format.format(eve1)));
                    weather.setEve(String.valueOf(format.format(night1)));
                    weather.setMorn(String.valueOf(format.format(morn1)));

                    Log.d("ListTemp",max+","+min);
                    JSONArray arrayweather=daily.getJSONArray("weather");
                    for (int j=0;j< arrayweather.length();j++){
                        JSONObject weatherdaily=arrayweather.getJSONObject(j);
                        String description="";
                        String icon="";
                        String main=" ";
                        main=weatherdaily.getString("main");
                        description=weatherdaily.getString("description");
                        icon=weatherdaily.getString("icon");
                        weather.setIcon(icon);
                        weather.setDescription(description);
                        weather.setMain(main);
                        Log.d("ListWeather",description+","+icon);

                    }
                    list_weather.add(weather);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                URL urlnew= new URL(params[1]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) urlnew.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(reader);
                String dong="";
                dulieu2=new StringBuilder();

                while ((dong=bufferedReader.readLine())!=null){
                    dulieu2.append(dong);
                }
                bufferedReader.close();
                reader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d("dulieu",dulieu2.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                JSONObject jsonObject=new JSONObject(dulieu2.toString());
                JSONObject omain=jsonObject.getJSONObject("main");
                temp=omain.getString("temp");
                temp1=Double.parseDouble(temp)- 273.15;

                humidity=omain.getString("humidity");
                JSONObject owind=jsonObject.getJSONObject("wind");
                speed=owind.getString("speed");

                JSONArray aweather=jsonObject.getJSONArray("weather");

                for (int k=0;k<aweather.length();k++){
                    JSONObject oweather=aweather.getJSONObject(k);
                    main2=oweather.getString("main");
                    description=oweather.getString("description");
                    icon=oweather.getString("icon");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list_weather;
        }

        @Override
        protected void onPostExecute(ArrayList<Weather> s) {
            if (pDialog.isShowing())
                pDialog.dismiss();
//            adapter.isEmpty();
            txtdiachi.setText(edtnhap.getText().toString()+","+" "+name+","+" "+country);
            imgvhumidity.setImageResource(R.drawable.humidity);
            imgvspeed.setImageResource(R.drawable.windspeed);
            txtdubao.setVisibility(View.VISIBLE);
            txtline.setVisibility(View.VISIBLE);
            txtdubao.setText("Dự báo");
            adapter=new Adapter(WeatherActivity.this,R.layout.lv_weather,list_weather);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            txtmain.setText(main2);
            txtdescription.setText(description);
            txttemp.setText(String.valueOf(format.format(temp1))+"°");
            txthumidity.setText(humidity+" "+"%");
            txtspeed.setText(speed+" "+"km/h");
            Picasso.with(WeatherActivity.this).load("http://openweathermap.org/img/w/"+icon+".png").into(imgvcurrent);
            Date date = new Date();
            String strDateFormat24 = "HH:mm:ss a";
            SimpleDateFormat sdf =null;
            sdf = new SimpleDateFormat(strDateFormat24);
            txttime.setText(sdf.format(date));
            Calendar ngay=Calendar.getInstance();
            int day1=ngay.get(Calendar.DATE);
            listday.add(String.valueOf(day1));listday.add(String.valueOf(day1 +1));listday.add(String.valueOf(day1+2));listday.add(String.valueOf(day1+3));listday.add(String.valueOf(day1 +4));
            listday.add(String.valueOf(day1 +5));listday.add(String.valueOf(day1 +6));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(WeatherActivity.this, WeatherDetail.class);
                    String diachi=edtnhap.getText().toString() + "," + name + "," + country;
                    intent.putExtra("diachi", diachi);
                    intent.putExtra("main",list_weather.get(position).getMain());
                    intent.putExtra("description",list_weather.get(position).getDescription());
                    intent.putExtra("iconid",list_weather.get(position).getIcon());
                    intent.putExtra("max",list_weather.get(position).getMax());
                    intent.putExtra("min",list_weather.get(position).getMin());
                    intent.putExtra("day",list_weather.get(position).getDay());
                    intent.putExtra("night",list_weather.get(position).getNigth());
                    intent.putExtra("eve",list_weather.get(position).getEve());
                    intent.putExtra("morn",list_weather.get(position).getMorn());
                    intent.putExtra("humidity",list_weather.get(position).getHumidity());
                    intent.putExtra("speed",list_weather.get(position).getSpeed());
                    intent.putExtra("ngay",listday.get(position));


                    startActivity(intent);

                }
            });
            super.onPostExecute(s);

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(this,"Chọn địa điểm khác",Toast.LENGTH_SHORT).show();
                layoutnhap.setVisibility(View.VISIBLE);
                break;
            default:
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
