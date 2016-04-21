package Adapter;

import java.util.ArrayList;

import example.phanmemquanlythuchi.R;

import Object.BaoCao;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Baocaohientai extends ArrayAdapter<BaoCao>{
	Context context;   
	 int layoutResourceId;   
	  ArrayList<BaoCao> listData = null;
	 public Baocaohientai(Context context, int layoutResourceId, ArrayList<BaoCao> data) {
	  super(context, layoutResourceId, data);
	  this.context=context;
	  this.layoutResourceId=layoutResourceId;
	  this.listData=data;    
	 }
	 @Override
	  public int getCount() {
	   
	   return this.listData.size();
	  }
	  @Override
	  public BaoCao getItem(int position) {
	   // TODO Auto-generated method stub
	   return listData.get(position);
	  }
	  
	  @Override
	  public long getItemId(int position) {
	   return position;
	  }
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	   View row=convertView;
	   ItemHolder holder=null;
	   if(row==null)
	   {
	    LayoutInflater inflater=((Activity)context).getLayoutInflater();
	    row=inflater.inflate(layoutResourceId, parent,false);
	    holder=new ItemHolder();
	    holder.title=(TextView)row.findViewById(R.id.textView_title_hientai1);
	    holder.ngay=(TextView)row.findViewById(R.id.textView_ngayhientai1);
	    holder.tienthu = (TextView) row.findViewById(R.id.textView_tienthuhientai1);
	    holder.tienchi= (TextView) row.findViewById(R.id.textView_tienchihientai1);
	    row.setTag(holder);
	   }
	   else
	   {
	    holder=(ItemHolder)row.getTag();
	   }
	   BaoCao item=listData.get(position);
	   holder.title.setText(listData.get(position).getTitle());
	   holder.ngay.setText(listData.get(position).getNgay());
	   holder.tienthu.setText(listData.get(position).getTienthu());
	   holder.tienchi.setText(listData.get(position).getTienchi());
	   return row;
	  }
	  static class ItemHolder
	  {
	    TextView title;
	    TextView ngay;
		TextView tienthu;
		TextView tienchi;
		
	  }
}
