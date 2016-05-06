package com.test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Legendary on 05/05/2016.
 */
public class ListItemAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<?> myList;
    int layoutId;

    public ListItemAdapter(Activity context, ArrayList<?> myList, int layoutId) {
        super(context, layoutId, myList);
        this.context = context;
        this.layoutId = layoutId;
        this.myList = myList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, parent, false);

            holder = new ViewHolder();
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.cost = (TextView) convertView.findViewById(R.id.cost);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        TextView type;
        TextView cost;
    }
}
