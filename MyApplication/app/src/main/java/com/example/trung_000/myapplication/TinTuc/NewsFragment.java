package com.example.trung_000.myapplication.TinTuc;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.trung_000.myapplication.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    ListView lv;
    InputStream inputStream;
    List<BaiViet> dsBaiViet;
    BaiViet baiViet;
    String noidung;
    AdapterTinTuc adapterTinTuc;
    String link="http://vnexpress.net/rss/du-lich.rss";
//    TextView txt;



    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_news, container, false);
//        txt=(TextView)v.findViewById(R.id.txt);
        ConnectivityManager cm=(ConnectivityManager)getActivity().getSystemService(NewsFragment.this.getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo nifto=cm.getActiveNetworkInfo();
        if(nifto!=null && nifto.isConnected()){
            Toast.makeText(NewsFragment.this.getActivity(),"Đã kết nối",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(NewsFragment.this.getActivity(),"Kiểm tra lại mạng 3G hoặc Wifi",Toast.LENGTH_SHORT).show();
        }
        setHasOptionsMenu(true);
        lv=(ListView)v.findViewById(R.id.lvtintuc);
        dsBaiViet=new ArrayList<BaiViet>();
        LayDuLieuXMLAS layDuLieuXMLAS = new LayDuLieuXMLAS();
        layDuLieuXMLAS.execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NewsFragment.this.getActivity(),dsBaiViet.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(NewsFragment.this.getActivity(),NewsActivity.class);
                String link = dsBaiViet.get(position).getLink();
                intent.putExtra("links", link);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
    public class LayDuLieuXMLAS extends AsyncTask<String,Void,List<BaiViet>> {

        @Override
        protected List<BaiViet> doInBackground(String... params) {
            dsBaiViet=new ArrayList<BaiViet>();
            try {

                URL url=new URL(link);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.connect();
                inputStream=httpURLConnection.getInputStream();
//
                XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser=factory.newPullParser();
                xmlPullParser.setInput(inputStream,null);

                int event=xmlPullParser.getEventType();
                while (event != XmlPullParser.END_DOCUMENT){
                    switch (event){
                        case XmlPullParser.START_TAG:
                            String themo=xmlPullParser.getName();
                            Log.d("themo",themo);
                            if(themo.equals("item")){
                                baiViet=new BaiViet();}
//                                }else if(themo.equals("title")){
//
//                                }
//                                Log.d("the mo","ma event"+event+"ten the"+ xmlPullParser.getName());
                            break;
                        case XmlPullParser.TEXT:
                            noidung=xmlPullParser.getText();
//                                Log.d("doan text","ma event "+ event + "ten the" + xmlPullParser.getText());
                            break;
                        case XmlPullParser.END_TAG:
                            String thedong=xmlPullParser.getName();
                            if(thedong.equals("item")&&baiViet!=null){
                                dsBaiViet.add(baiViet);


                            }else if (thedong.equals("title")&&baiViet!=null){
                                baiViet.setTitle(noidung);
                            }else if(thedong.equals("description")&&baiViet!=null){
                                String detail;
                                int start=noidung.indexOf("r>");

                                detail=noidung.substring(start+2,start+2 +50);
                                Log.d("chuoi cắt",detail);
                                String linkanh;
                                int start1=noidung.indexOf("http://img");
                                int end1=noidung.indexOf(".jpg");
                                int end2=noidung.indexOf(".png");
                                int end3=noidung.indexOf(".jpeg");

                                if(end1 >= 0){
                                    linkanh=noidung.substring(start1,end1+4);
                                    baiViet.setImagelink(linkanh);
                                    Log.d("chuoicat",linkanh);
                                }else if((end1 <0 )&&(end3 <0)){
                                    linkanh=noidung.substring(start1,end2+4);
                                    baiViet.setImagelink(linkanh);
                                }else if((end1<0)&&(end2<0)){
                                    linkanh=noidung.substring(start1,end3+5);
                                    baiViet.setImagelink(linkanh);
                                }


                                baiViet.setDecription(detail);
                            }else if(thedong.equals("pubDate")&&baiViet!=null){
                                baiViet.setPubDate(noidung);
                            }else if(thedong.equals("link")&&baiViet!=null){
                                baiViet.setLink(noidung);
                            }
//                                Log.d("the dong","ma event"+event+"ten the "+ xmlPullParser.getName());
                            break;

                    }
                    event=xmlPullParser.next();
                }

                for(int i =0;i < dsBaiViet.size();i++ ){
                    Log.d("du lieu",dsBaiViet.get(i).getTitle());
                    Log.d("date",dsBaiViet.get(i).getPubDate());
                    Log.d("noi dung",dsBaiViet.get(i).getDecription());
//                        Log.d("imglink",dsBaiViet.get(i).getImagelink());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return dsBaiViet;
        }

        @Override
        protected void onPostExecute(List<BaiViet> ketqua) {
            super.onPostExecute(ketqua);
            adapterTinTuc=new AdapterTinTuc(NewsFragment.this.getActivity(),R.layout.custom_lv_new,dsBaiViet);
//            SwingLeftInAnimationAdapter animationAdapter=new SwingLeftInAnimationAdapter(adapterTinTuc);
//            animationAdapter.setAbsListView(lv);
            lv.setAdapter(adapterTinTuc);

            adapterTinTuc.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.baomoi:
                Toast.makeText(NewsFragment.this.getActivity(),"Báo mới",Toast.LENGTH_SHORT).show();


//                txt.setVisibility(View.VISIBLE);

                break;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.newsmenu,menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

}
