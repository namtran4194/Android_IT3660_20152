package com.example.trung_000.myapplication.MienNam;


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
public class MiennamFragment extends Fragment {
    ViewFlipper viewFlipper;
    ImageView imgvdnb,imgvtnb;
    Button btnnext,btnprev;
    ImageView image2,image3,image4,image5,image6;


    public MiennamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_miennam, container, false);
        image2=(ImageView)v.findViewById(R.id.anh1);
        PicassoClient.downloadImage(MiennamFragment.this.getActivity(),
                "http://files.vforum.vn/2013/T12/img/diendanbaclieu-107447-8568378214-1b7a2e175f-o.jpg",image2);
        image3=(ImageView)v.findViewById(R.id.anh2);
        PicassoClient.downloadImage(MiennamFragment.this.getActivity(),
                "https://www.svietnamtravel.com/images/destinations/vungtau/tong-hop-cac-khach-san-nha-nghi-tai-thanh-pho-ba-ria-vung-tau.jpg",image3);
        image4=(ImageView)v.findViewById(R.id.anh3);
        PicassoClient.downloadImage(MiennamFragment.this.getActivity(),
                "https://cdn3.ivivu.com/2015/09/nhaydonmua-buom-rungnhaydon-o-rung-nam-cat-tien-ivivu-2.jpg",image4);
        image5=(ImageView)v.findViewById(R.id.anh4);
        PicassoClient.downloadImage(MiennamFragment.this.getActivity(),
                "http://media6.tiin.vn/medias12//2015/10/28/933c9f82-ba0e-4578-8364-11f3d7ab849e.jpg",image5);
        image6=(ImageView)v.findViewById(R.id.anh5);
        PicassoClient.downloadImage(MiennamFragment.this.getActivity(),
                "https://1.bp.blogspot.com/-9jPOwWgko20/VDzj5QyfngI/AAAAAAAAIzo/iYTfk7sDBeg/s1600/dinh-cau-phu-quoc-2.jpg",image6);
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
        imgvdnb=(ImageView)v.findViewById(R.id.imgvdnb);
        imgvdnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MiennamFragment.this.getActivity(),"Du lịch Đông Nam Bộ",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MiennamFragment.this.getActivity(), DongNamBoActivity.class);
                startActivity(myIntent);
            }
        });
        imgvtnb=(ImageView)v.findViewById(R.id.imgvtnb);
        imgvtnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MiennamFragment.this.getActivity(),"Du lịch Tây Nam Bộ",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MiennamFragment.this.getActivity(), TayNamBoActivity.class);
                startActivity(myIntent);
            }
        });
        viewFlipper=(ViewFlipper)v.findViewById(R.id.idviewflippermiennam);
        viewFlipper.setInAnimation(MiennamFragment.this.getActivity(),android.R.anim.fade_in);
        viewFlipper.setOutAnimation(MiennamFragment.this.getActivity(),android.R.anim.fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(4000);
        // Inflate the layout for this fragment
        return v;
    }

}
