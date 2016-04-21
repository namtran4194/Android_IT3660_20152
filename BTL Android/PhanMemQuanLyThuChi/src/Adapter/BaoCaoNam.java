package Adapter;

import java.util.ArrayList;

import Object.BaoCao;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import example.phanmemquanlythuchi.R;

public class BaoCaoNam extends ArrayAdapter<BaoCao>{
	Context context;   
	 int layoutResourceId;   
	  ArrayList<BaoCao> listData = null;
	  
	 public BaoCaoNam(Context context, int layoutResourceId, ArrayList<BaoCao> data) {
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
	    holder.title=(TextView)row.findViewById(R.id.textView_nam);
	    holder.hieuthuchi=(TextView)row.findViewById(R.id.textView_nam_thuchi);
	    holder.tienthu = (TextView) row.findViewById(R.id.textView_namthu);
	    holder.tienchi= (TextView) row.findViewById(R.id.textView_namchi);
	    row.setTag(holder);
	   }
	   else
	   {
	    holder=(ItemHolder)row.getTag();
	   }
	   DoiNgay doi=new DoiNgay();
	   BaoCao item=listData.get(position);
	   holder.title.setText(doi.doiNam1(listData.get(position).getNgay()));
	   holder.hieuthuchi.setText(String.valueOf(Double.parseDouble(listData.get(position).getTienthu())-Double.parseDouble(listData.get(position).getTienchi())));
	   holder.tienthu.setText(listData.get(position).getTienthu());
	   holder.tienchi.setText(listData.get(position).getTienchi());
	   return row;
	  }
	  static class ItemHolder
	  {
	    TextView title;
	    TextView hieuthuchi;
		TextView tienthu;
		TextView tienchi;
		
	  }
}
