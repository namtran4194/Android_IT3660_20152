package com.example.trung_000.myapplication.Weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trung_000.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by trung_000 on 5/13/2016.
 */
public class Adapter extends BaseAdapter {
    Context context;
    int layout;
    List<Weather>arrday;

    public Adapter(WeatherActivity weatherActivity, int lv_weather, List<Weather> list_day) {
        this.context=weatherActivity;
        this.layout=lv_weather;
        this.arrday=list_day;
    }

    @Override
    public int getCount() {
        return arrday.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Calendar ngay=Calendar.getInstance();
        int day1=ngay.get(Calendar.DATE);
        ArrayList<Integer>listday=new ArrayList<Integer>();
        listday.add(day1);listday.add(day1+1);listday.add(day1+2);listday.add(day1+3);listday.add(day1+4);
        listday.add(day1+5);listday.add(day1+6);
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=layoutInflater.inflate(R.layout.lv_weather,parent,false);
        TextView txtngay=(TextView)v.findViewById(R.id.txtngay);
        txtngay.setText("Ngày"+" "+String.valueOf(listday.get(position)));
        TextView txtmax=(TextView)v.findViewById(R.id.max);
        TextView txtmin=(TextView)v.findViewById(R.id.min);
        txtmax.setText(arrday.get(position).getMax()+"°");
        txtmin.setText(arrday.get(position).getMin()+"°");
//        TextView txtmieuta=(TextView)v.findViewById(R.id.txtmieuta);
//        txtmieuta.setText(arrday.get(position).getDescription());
//        TextView txtmain=(TextView)v.findViewById(R.id.txtmain);
//        txtmain.setText(arrday.get(position).getMain());
        ImageView imgv=(ImageView)v.findViewById(R.id.imgv);
        Picasso.with(context).load("http://openweathermap.org/img/w/"+arrday.get(position).getIcon()+".png").into(imgv);


        return v;
    }
}
