package com.example.trung_000.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ActiviyAppIntro extends AppCompatActivity {
    ViewPager viewPager;
    List<Fragment>fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiy_app_intro);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        fragmentList=new ArrayList<Fragment>();
        Fragment1 fragment1=new Fragment1();
        Fragment2 fragment2=new Fragment2();
        Fragment3 fragment3=new Fragment3();
        Fragment4 fragment4=new Fragment4();
        Fragment5 fragment5=new Fragment5();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        AdapterViewPager adapterViewPager=new AdapterViewPager(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewPager);
        viewPager.setPageTransformer(true,new DepthAnimation());
        }

    }

