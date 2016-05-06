package com.test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Legendary on 05/05/2016.
 */
public class ListAllAdapter extends ArrayAdapter {
    Activity context;
    ArrayList<?> myList;
    int layoutId;

    public ListAllAdapter(Activity context, ArrayList<?> myList, int layoutId) {
        super(context, layoutId, myList);
        this.context = context;
        this.layoutId = layoutId;
        this.myList = myList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderAll holder;
        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, parent, false);

            holder = new ViewHolderAll();
            holder.date = (TextView) convertView.findViewById(R.id.type);
            holder.totalMoney = (TextView) convertView.findViewById(R.id.cost);
            holder.items = (ListView) convertView.findViewById(R.id.items);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderAll) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolderAll {
        TextView date;
        TextView totalMoney;
        ListView items;
    }
}
