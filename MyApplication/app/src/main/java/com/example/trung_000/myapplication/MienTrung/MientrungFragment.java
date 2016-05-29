package com.example.trung_000.myapplication.MienTrung;


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
public class MientrungFragment extends Fragment {
    ImageView imgvbtb,imgvtn,imgvntb;
    ViewFlipper viewFlipper;
    Button btnnext,btnprev;
    ImageView image2,image3,image4,image5,image6;


    public MientrungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mientrung, container, false);
        image2=(ImageView)v.findViewById(R.id.anh1);
        PicassoClient.downloadImage(MientrungFragment.this.getActivity(),
                "https://4.bp.blogspot.com/-aabtYTe0KBI/VDIZGtKO8mI/AAAAAAAAIdI/WVwd9ALauzE/s1600/dien-thai-hoa.jpg",image2);
        image3=(ImageView)v.findViewById(R.id.anh2);
        PicassoClient.downloadImage(MientrungFragment.this.getActivity(),
                "http://yeutre.vn/attachments/vung-chua-dao-yen-jpg.19460",image3);
        image4=(ImageView)v.findViewById(R.id.anh3);
        PicassoClient.downloadImage(MientrungFragment.this.getActivity(),
                "http://xspace.talaweb.com/giaydantuongdalatadmin/home/thunglungtinhyeu1.jpg",image4);
        image5=(ImageView)v.findViewById(R.id.anh4);
        PicassoClient.downloadImage(MientrungFragment.this.getActivity(),
                "http://duli.vn/attachments/15575326026_c8a8ff4baf_h-jpg.84313",image5);
        image6=(ImageView)v.findViewById(R.id.anh5);
        PicassoClient.downloadImage(MientrungFragment.this.getActivity(),
                "https://static.mytour.vn/upload_images/Image/Location/29_5_2015/Doi-cat-bay-mui-ne-phan-thiet-mytour-1.jpg",image6);
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
        imgvbtb=(ImageView)v.findViewById(R.id.imgvbtb) ;
        imgvbtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MientrungFragment.this.getActivity(),"Du lịch Bắc Trung Bộ",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MientrungFragment.this.getActivity(), BacTrungBoActivity.class);
                startActivity(myIntent);
            }
        });
        imgvtn=(ImageView)v.findViewById(R.id.imgvtaynguyen);
        imgvtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MientrungFragment.this.getActivity(),"Du lịch Tây Nguyên",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MientrungFragment.this.getActivity(), TayNguyenActivity.class);
                startActivity(myIntent);
            }
        });
        imgvntb=(ImageView)v.findViewById(R.id.imgvntb);
        imgvntb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MientrungFragment.this.getActivity(),"Du lịch Nam Trung Bộ",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MientrungFragment.this.getActivity(), NamTrungBoActivity.class);
                startActivity(myIntent);
            }
        });
        viewFlipper=(ViewFlipper)v.findViewById(R.id.idviewflippermientrung);
        viewFlipper.setInAnimation(MientrungFragment.this.getActivity(),android.R.anim.fade_in);
        viewFlipper.setOutAnimation(MientrungFragment.this.getActivity(),android.R.anim.fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(4000);
        // Inflate the layout for this fragment
        return v;
    }

}
