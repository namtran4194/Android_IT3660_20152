package com.example.trung_000.myapplication.MienNam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trung_000.myapplication.R;
import com.squareup.picasso.Picasso;

/**
 * Created by trung_000 on 5/7/2016.
 */
public class TNBAdapter extends BaseAdapter{
    Context context;
    int layout;
    String []link;
    String []title;
    int []detail;
    public TNBAdapter(TayNamBoActivity tayNamBoActivity, int lvtaybac, String[] title, int[] detail, String[] link) {
        this.context=tayNamBoActivity;
        this.title=title;
        this.detail=detail;
        this.link=link;
        this.layout=lvtaybac;

    }

    @Override
    public int getCount() {
        return title.length;
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
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.lvtaybac,parent,false);
        ImageView imgv1=(ImageView)convertView.findViewById(R.id.imgv1);
        Picasso.with(context).load(link[position]).into(imgv1);
        TextView txt1=(TextView)convertView.findViewById(R.id.txt1);
        txt1.setText(title[position]);
        TextView txt2=(TextView)convertView.findViewById(R.id.txt2);
        txt2.setText(detail[position]);
        return convertView;
    }
}
