package com.example.trung_000.myapplication.MienBac;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.trung_000.myapplication.PicassoClient;
import com.example.trung_000.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MienbacFragment extends Fragment {
    ImageView imgvtaybac,imgvdongbac,imgvdb,image3,image2,image4,image5,image6;
    ViewFlipper viewFlipper;
    Button btnnext,btnprev;
    float toadox1;
    float toadox2;



    public MienbacFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mienbac, container, false);
        image2=(ImageView)v.findViewById(R.id.image2);
        PicassoClient.downloadImage(MienbacFragment.this.getActivity(),
                "http://mocchautourism.com/uploads/news/2012_04/chau.jpg",image2);
        image3=(ImageView)v.findViewById(R.id.image3);
        PicassoClient.downloadImage(MienbacFragment.this.getActivity(),
                "http://thanhlamhotspring.com/wp-content/uploads/2015/10/du-lich-ha-giang-xuan2_1422342738.jpg",image3);
        image4=(ImageView)v.findViewById(R.id.image4);
        PicassoClient.downloadImage(MienbacFragment.this.getActivity(),
                "http://dulichtamdao.vn/documents/1385537/0/dkl1366709606.jpg",image4);
        image5=(ImageView)v.findViewById(R.id.image5);
        PicassoClient.downloadImage(MienbacFragment.this.getActivity(),
                "http://2.bp.blogspot.com/-5EIAQDvJIbI/UdG3FiTlW6I/AAAAAAAAAXk/0oJlzgH5cAo/s1600/Vinh-Ha-long-1.jpg",image5);
        image6=(ImageView)v.findViewById(R.id.image6);
        PicassoClient.downloadImage(MienbacFragment.this.getActivity(),
                "https://cdn3.ivivu.com/2014/10/du-lich-sa-pa-cam-nang-tu-a-den-z-iVIVU.com-1.jpg",image6);
        btnnext=(Button)v.findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });
        btnprev=(Button)v.findViewById(R.id.btnprev) ;
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
            }
        });
        imgvdongbac=(ImageView)v.findViewById(R.id.dongbac);
        imgvdongbac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MienbacFragment.this.getActivity(),"Du lịch Đông Bắc",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MienbacFragment.this.getActivity(), DongBacActivity.class);
                startActivity(myIntent);
            }
        });
        imgvdb=(ImageView)v.findViewById(R.id.dbsonghong);
        imgvdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MienbacFragment.this.getActivity(),"Du lịch ĐB Sông Hồng",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MienbacFragment.this.getActivity(), DBSongHongActivity.class);
                startActivity(myIntent);
            }
        });
        imgvtaybac=(ImageView)v.findViewById(R.id.taybac);
        imgvtaybac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MienbacFragment.this.getActivity(),"Du lịch Tây Bắc",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MienbacFragment.this.getActivity(), TayBacActivity.class);
                startActivity(myIntent);
            }
        });
        viewFlipper=(ViewFlipper)v.findViewById(R.id.idviewflipper);
        viewFlipper.setInAnimation(MienbacFragment.this.getActivity(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(MienbacFragment.this.getActivity(),android.R.anim.slide_out_right);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(4000);
//        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()){
//                    case MotionEvent.ACTION_DOWN:
//                        toadox1=event.getX();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        toadox2=event.getX();
//                        if(toadox2<toadox1){
//                            viewFlipper.showPrevious();
//                        }
//                        else {
//                            viewFlipper.showNext();
//                        }
//                        break;
//                }
//                return false;
//            }
//        });

        // Inflate the layout for this fragment
        return v;


    }

}
