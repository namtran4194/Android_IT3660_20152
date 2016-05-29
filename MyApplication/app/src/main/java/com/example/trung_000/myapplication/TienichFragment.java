package com.example.trung_000.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.trung_000.myapplication.Weather.WeatherActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TienichFragment extends Fragment {
    ImageView imgvmaps,imgvweather,imgvcho;


    public TienichFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_tienich, container, false);
        imgvmaps=(ImageView)v.findViewById(R.id.imgvmap);
        imgvweather=(ImageView)v.findViewById(R.id.imgvthoitiet);
        imgvmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TienichFragment.this.getActivity(),MapsActivity.class);
                startActivity(intent);
            }
        });
        imgvweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TienichFragment.this.getActivity(),WeatherActivity.class);
                startActivity(intent);
            }
        });
        imgvcho=(ImageView)v.findViewById(R.id.imgvcho);
        imgvcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TienichFragment.this.getActivity(),Lichchophien.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
