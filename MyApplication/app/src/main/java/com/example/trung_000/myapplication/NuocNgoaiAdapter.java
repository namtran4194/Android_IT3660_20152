package com.example.trung_000.myapplication;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by trung_000 on 5/8/2016.
 */
public class NuocNgoaiAdapter extends BaseAdapter {
    Context context;
    String []title;
    String []link;
    int []detail;
    private LayoutInflater layoutInflater;
    public NuocNgoaiAdapter(FragmentActivity activity, String[] title, int[] detail, String[] link) {
        this.context=activity;
        this.title=title;
        this.link=link;
        this.detail=detail;
        this.layoutInflater=LayoutInflater.from(activity);
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
        convertView=inflater.inflate(R.layout.customlv,parent,false);
        ImageView imgv1=(ImageView)convertView.findViewById(R.id.imgv1);
        PicassoClient.downloadImage(context,link[position],imgv1);
        TextView txt1=(TextView)convertView.findViewById(R.id.txt1);
        txt1.setText(title[position]);
        TextView txt2=(TextView)convertView.findViewById(R.id.txt2);
        txt2.setText(detail[position]);
        return convertView;
    }
}
