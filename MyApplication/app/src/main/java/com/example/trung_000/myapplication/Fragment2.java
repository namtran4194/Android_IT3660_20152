package com.example.trung_000.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    Button btnskip,btnnext;


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_fragment2, container, false);
        btnskip=(Button)v.findViewById(R.id.btnskip);
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Fragment2.this.getActivity(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                Fragment2.this.getActivity().finish();
                startActivity(intent);
            }
        });
        btnnext=(Button)v.findViewById(R.id.btnnext);
//        btnnext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Fragment2.this.getActivity(),Fragment3.class);
//                startActivity(intent);
//
//            }
//        });
        // Inflate the layout for this fragment
        return v;
    }

}
