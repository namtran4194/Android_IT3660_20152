package com.example.trung_000.myapplication;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by trung_000 on 5/9/2016.
 */
public class PicassoClient {
    public static void downloadImage(Context c, String url, ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.defaultimage).into(img);
        }else
        {
            Picasso.with(c).load(R.drawable.defaultimage).into(img);
        }
    }
}
