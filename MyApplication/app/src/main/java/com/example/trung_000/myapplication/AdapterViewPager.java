package com.example.trung_000.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by trung_000 on 5/18/2016.
 */
public class AdapterViewPager extends FragmentStatePagerAdapter {
    List<Fragment> list;
    public AdapterViewPager(FragmentManager fm,List<Fragment>list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
