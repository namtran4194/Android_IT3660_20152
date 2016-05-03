package com.nam.hust.demolist;

import android.content.Context;
import android.support.v7.widget.ListViewCompat;

/**
 * Created by Legendary on 02/05/2016.
 */
public class MyListView extends ListViewCompat {
    Context context;

    public MyListView(Context context) {
        super(context);
        this.context = context;
    }

}
