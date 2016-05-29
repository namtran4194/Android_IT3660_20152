package com.example.trung_000.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpFragment extends Fragment {
    ListView lv;
    String []title={"8 ứng dụng điện thoại cần thiết cho dân du lịch","Cho những bạn lần đầu đi phượt","Kinh nghiệm chuẩn bị đồ đi phượt",
                    "Kinh nghiệm đi xe máy trên đường phượt","Kinh nghiệm gửi xe máy theo ô tô tàu hỏa","Kinh nghiệm đặt khách sạn online",
                    "Các quy định cần biết khi vào khu vực biên giới","Cách nhận biết dòng chảy xa bờ","Biển hiệu lệnh đường bộ Việt Nam",
                    "Biển báo cấm đường bộ Việt Nam","Biển báo nguy hiểm đường bộ Việt Nam","Một số kinh nghiệm cho lần đầu đi nước ngoài",
                    "Lưu ý khi đi máy bay lần đầu","Những điều cần lưu ý cho chuyến du lịch bụi nước ngoài lần đầu"};
    String []link={"https://cdn3.ivivu.com/2015/02/10-meo-nho-huu-dung-khi-di-du-lich-Tet-ivivu-5-370x215.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/DSC_0577.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/chuan-bi-do-khi-di-phuot.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/kinh-nghiem-di-xe-may-tren-duong-phuot.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/Gui-xe-may.jpg",
                    "https://cungphuot.info/wp-content/uploads/2015/01/dat-phong-4.jpg",
                    "https://cungphuot.info/wp-content/uploads/2015/11/khu-vuc-bien-gioi.jpg",
                    "https://cungphuot.info/wp-content/uploads/2015/03/dong-chay-xa-bo.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/bien-hieu-lenh.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/bien-bao-cam.jpg",
                    "https://cungphuot.info/wp-content/uploads/2014/07/bien-bao-nguy-hiem.jpg",
                    "https://cdn3.ivivu.com/2012/05/reisredenen1.jpg",
                    "https://cdn3.ivivu.com/2016/04/luu-y-khi-di-may-bay-lan-dau-ivivu-1-370x215.jpg",
                    "https://cdn3.ivivu.com/2014/05/du-lich-bui-lan-dau-ivivu2-370x215.jpg"};
    int []detail={R.string.tip1,R.string.tip2,R.string.tip3,R.string.tip4,R.string.tip5,R.string.tip6,R.string.tip7,R.string.tip8,R.string.tip9
                ,R.string.tip10,R.string.tip11,R.string.tip12,R.string.tip13,R.string.tip14};
    ArrayList<String> arrayListLinks = new ArrayList<>();


    public ExpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_exp, container, false);
        lv=(ListView)v.findViewById(R.id.lvexp);
        ExpAdapter adapter=new ExpAdapter(ExpFragment.this.getActivity(),title,detail,link);
        SwingBottomInAnimationAdapter animationAdapter=new SwingBottomInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        arrayListLinks.add("http://hanhtrangphuot.tk/app.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/kinhnghiemlandaudiphuot.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/kinhnghiemchuanbido.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/kinhnghiemphuotxemay.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/kinhnghiemguixemay.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/kinhnghiemdatkhacsanol.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/cacquydinhcanbiet.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/cachnhanbietdongchayxabo.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/bienhieulenh.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/bienbaocamduongbo.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/bienbaonguyhiemduongbo.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/kinhnghiemlandaudinuocngoai.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/dimaybaylandau.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/dulichbuinuocngoai.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ExpFragment.this.getActivity(),"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExpFragment.this.getActivity(), Taybac.class);
                String link = arrayListLinks.get(position);
                intent.putExtra("links", link);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}
