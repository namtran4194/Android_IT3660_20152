package com.example.trung_000.myapplication.TinTuc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trung_000.myapplication.PicassoClient;
import com.example.trung_000.myapplication.R;

import java.util.List;

/**
 * Created by trung_000 on 5/11/2016.
 */
public class AdapterTinTuc extends BaseAdapter {
    Context context;
    int layout;
    List<BaiViet> dsBaiViet;
    ViewHolder holder;

    private class ViewHolder{
        TextView txtTieude,txtdate,txtnoidung;
        ImageView imgv;
    }

    public AdapterTinTuc(Context context, int layout, List<BaiViet> dsBaiViet) {
        this.dsBaiViet = dsBaiViet;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dsBaiViet.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if ((view==null)){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(layout,parent,false);
            holder= new ViewHolder();
            holder.txtTieude=(TextView)view.findViewById(R.id.txtTieude);
            holder.txtdate=(TextView)view.findViewById(R.id.txtdate);
            holder.txtnoidung=(TextView)view.findViewById(R.id.txtnoidung);
            holder.imgv=(ImageView)view.findViewById(R.id.imgv);
            view.setTag(holder);
        }
            holder=(ViewHolder)view.getTag();
            holder.txtTieude.setText(dsBaiViet.get(position).getTitle());
            holder.txtdate.setText(dsBaiViet.get(position).getPubDate());
            holder.txtnoidung.setText(dsBaiViet.get(position).getDecription());
            PicassoClient.downloadImage(context,dsBaiViet.get(position).getImagelink(),holder.imgv);
//            String imglink=dsBaiViet.get(position).getImagelink();
//            DownloadAnh downloadAnh=new DownloadAnh();
//            downloadAnh.execute(imglink);

        return view;
    }
//    public  class DownloadAnh extends AsyncTask<String,Void,Bitmap> {
//        Bitmap downhinh;
//        @Override
//        protected Bitmap doInBackground(String... params) {
//
//            try {
//                URL url=new URL(params[0]);
//                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
//                httpURLConnection.connect();
//                InputStream inputStream=httpURLConnection.getInputStream();
//                downhinh= BitmapFactory.decodeStream(inputStream);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return downhinh;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            holder.imgv.setImageBitmap(bitmap);
//        }
//    }
}
