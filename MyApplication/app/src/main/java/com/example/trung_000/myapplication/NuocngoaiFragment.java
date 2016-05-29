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
public class NuocngoaiFragment extends Fragment {
    ListView lv;
    String []title={"Cẩm nang du lịch Campuchia từ A đến Z","Cẩm nang du lịch Indonesia từ A đến Z","Cẩm nang du lịch Lào từ A đến Z",
                    "Cẩm nang du lịch Malaysia từ A đến Z","Cẩm nang du lịch Myanmar từ A đến Z","Cẩm nang du lịch Philipines từ A đến Z",
                    "Cẩm nang du lịch Singapore từ A đến Z","Cẩm nang du lịch Thái Lan từ A đến Z","Cẩm nang du lịch Hàn Quốc từ A đến Z",
                    "Cẩm nang du lịch Nhật Bản từ A đến Z","Cẩm nang du lịch Đài Loan từ A đến Z","Cẩm nang du lịch Trung Quốc từ A đến Z",
                    "Cẩm nang du lịch Hông Kông từ A đến Z"};
    String []link={"https://cdn3.ivivu.com/2013/10/Campuchia-ivivu-250x142.jpg",
                    "https://cdn3.ivivu.com/2015/10/du-lich-indonesia-ivivu.com-1-370x215.jpg",
                    "https://cdn3.ivivu.com/2015/10/Victoria-Xiengthong-Palace-ivivu-5.jpg",
                    "https://cdn3.ivivu.com/2013/10/du-lich-malaysia.jpg",
                    "https://cdn3.ivivu.com/2013/10/du-lich-myanmar-featured.jpg",
                    "https://cdn3.ivivu.com/2015/07/Philippines-ivivu-4-370x215.jpg",
                    "https://cdn3.ivivu.com/2013/10/du-lich-singapore-featured.jpg",
                    "https://cdn3.ivivu.com/2015/10/bang-kok-ivivu.com-1.jpg",
                    "https://cdn3.ivivu.com/2013/04/1Jeju-Island-South-Korea-wallpaper.jpg",
                    "https://cdn3.ivivu.com/2016/03/du-xuan-duoi-noc-nha-nhat-ban-ivivu-1-370x215.jpg",
                    "http://yeutre.vn/attachments/dai-loan-jpg.23883",
                    "https://cdn3.ivivu.com/2013/11/Trung-Quoc-nui-Nga-My.png",
                    "https://cdn3.ivivu.com/2013/10/du-lich-hong-kong.jpg",
                    };
    int[]detail={R.string.campuchia,R.string.indonesia,R.string.lao,R.string.malaysia,R.string.myanmar,
                R.string.philipines,R.string.singapore,R.string.thailan,R.string.hanquoc,R.string.nhatban,
                R.string.dailoan,R.string.trungquoc,R.string.hongkong};
    ArrayList<String>arrayListLinks=new ArrayList<>();


    public NuocngoaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_nuocngoai, container, false);
        lv=(ListView)v.findViewById(R.id.lvnuocngoai);
        NuocNgoaiAdapter adapter=new NuocNgoaiAdapter(NuocngoaiFragment.this.getActivity(),title,detail,link);
        SwingBottomInAnimationAdapter animationAdapter=new SwingBottomInAnimationAdapter(adapter);
        animationAdapter.setAbsListView(lv);
        lv.setAdapter(animationAdapter);
        arrayListLinks.add("http://hanhtrangphuot.tk/campuchia.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/indonesia.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/lao.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/malaysia.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/myanmar.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/philipines.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/singapore.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/thailan.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hanquoc.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/nhatban.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/dailoan.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/trungquoc.html");
        arrayListLinks.add("http://hanhtrangphuot.tk/hongkong.html");
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NuocngoaiFragment.this.getActivity(),"Bạn chọn"+" "+ title[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NuocngoaiFragment.this.getActivity(), Taybac.class);
                String link = arrayListLinks.get(position);
                intent.putExtra("links", link);
                startActivity(intent);
            }
        });
        return v;
    }

}
