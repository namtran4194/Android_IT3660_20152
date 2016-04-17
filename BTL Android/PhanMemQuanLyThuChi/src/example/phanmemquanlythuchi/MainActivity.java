package example.phanmemquanlythuchi;


import java.util.ArrayList;



import Adapter.Menu_adapter;
import Database.dbChi;
import Database.dbThu;
import Object.BaoCao;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;


public class MainActivity extends Activity {

 dbThu dbthu;
 SQLiteDatabase mDbthu;
 Cursor mCursorthu;
 //danh sach chi
 dbChi dbchi;
 SQLiteDatabase mDbchi;
 Cursor mCursorchi;

 private ArrayList<BaoCao>arrthu;
 private ArrayList<BaoCao>arrchi;
 BaoCao objectchi2;

 final static String[] mItemTexts = new String[] {
   "Thu nhập","Chi tiêu","Danh Sách",
         "Biều đồ","Đổi tiền tệ","Gửi tiết kiệm","Thoát"
        };
 final static int[] mItemImgs = new int[] {
   R.drawable.icon_thu,R.drawable.icon_chi,R.drawable.icon_danh_sach,
         R.drawable.icon_bieu_do,R.drawable.icon_tien_te,R.drawable.icon_bank,
         R.drawable.icon_exit
        };
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_thuchi);
  dbthu=new dbThu(this);
  dbchi=new dbChi(this);
  danhSachThu();
  danhSachChi();
  GridView grid=(GridView)findViewById(R.id.gridView_menu);
  Menu_adapter adapter=new Menu_adapter(this,mItemTexts , mItemImgs);
  grid.setAdapter(adapter);
  grid.setOnItemClickListener(new OnItemClickListener() {

   @Override
   public void onItemClick(AdapterView<?> arg0, View arg1, int position,
     long arg3) {
    // TODO Auto-generated method stub
    if(position==0){
     Intent chuyen=new Intent(MainActivity.this,TienThu.class);
     startActivity(chuyen);
    }else if(position==1){
     Intent chuyen=new Intent(MainActivity.this,Tienchi.class);
     startActivity(chuyen);
    }else if(position==2){
     Intent chuyen=new Intent(MainActivity.this,DanhSachThuChi.class);
     startActivity(chuyen);
    }else if(position==3){
     if(arrthu.size()==0||arrchi.size()==0){
      Toast toast=Toast.makeText(MainActivity.this, "danh sách rỗng", Toast.LENGTH_SHORT);
      toast.show();
     }else{
      Intent chuyen=new Intent(MainActivity.this,BaoCaoThuChi.class);
      startActivity(chuyen);
     }
    }else if(position==4){
     Intent chuyen=new Intent(MainActivity.this,NgoaiTe.class);
     startActivity(chuyen);
    }else if(position==5){
     Intent chuyen=new Intent(MainActivity.this,LaiXuat.class);
     startActivity(chuyen);
    }
    
    else if(position==7){
     //thoát chương trình
     AlertDialog.Builder exitDialog = new AlertDialog.Builder(MainActivity.this);
     exitDialog.setTitle("Thoát");
     exitDialog.setMessage("Bạn muốn thoát chương trình không?");
     exitDialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
       finish();
      }
     });
     exitDialog.setPositiveButton("Để sau", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
       
      }
     });
     exitDialog.setCancelable(false);
     exitDialog.show();
     //
    }
   }
  });
 }
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
  // Inflate the menu; this adds items to the action bar if it is present.
  getMenuInflater().inflate(R.menu.main_activity__thu_chi, menu);
  return true;
 }
 public void danhSachChi(){
  mDbchi = dbchi.getWritableDatabase();
  String querychi = "select * from chi";
  mCursorchi = mDbchi.rawQuery(querychi, null);
  arrchi=new ArrayList<BaoCao>();
  if (mCursorchi.moveToFirst()) {
            do {
             objectchi2=new BaoCao();
             objectchi2.setTienchi(mCursorchi.getString(2));
             objectchi2.setNgay(mCursorchi.getString(4));
             objectchi2.setNhom(mCursorchi.getString(3));
             arrchi.add(objectchi2);
            } while (mCursorchi.moveToNext());
  }
 }
 public void danhSachThu(){
  mDbthu = dbthu.getWritableDatabase();
  String query = "select * from thu";
  mCursorthu = mDbthu.rawQuery(query, null);
  arrthu=new ArrayList<BaoCao>();
  if (mCursorthu.moveToFirst()) {
            do {
             objectchi2=new BaoCao();
             objectchi2.setTienthu(mCursorthu.getString(2));
             objectchi2.setNgay(mCursorthu.getString(4));
             objectchi2.setNhom(mCursorthu.getString(3));
            arrthu.add(objectchi2);
            } while (mCursorthu.moveToNext());
        }
 }
}