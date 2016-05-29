package com.example.trung_000.myapplication;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trung_000.myapplication.MienBac.MienbacFragment;
import com.example.trung_000.myapplication.MienNam.MiennamFragment;
import com.example.trung_000.myapplication.MienTrung.MientrungFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrongnuocFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    MienbacFragment mienbacFragment;
    MientrungFragment mientrungFragment;
    MiennamFragment miennamFragment;



    public TrongnuocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trongnuoc, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return v;
        // Inflate the layout for this fragment

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        mienbacFragment = new MienbacFragment();
        mientrungFragment = new MientrungFragment();
        miennamFragment = new MiennamFragment();
        adapter.addFragment(mienbacFragment, "Miền Bắc");
        adapter.addFragment(mientrungFragment, "Miền Trung");
        adapter.addFragment(miennamFragment, "Miền Nam");
        viewPager.setAdapter(adapter);
    }

}
