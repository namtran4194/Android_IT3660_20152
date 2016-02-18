package hust.namtran.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by NamTX on 04/02/2016.
 */
public class ShowPicture extends Activity implements AdapterView.OnItemClickListener {
    Integer[] thumbIds;
    TextView tvMsg, tvSoloMsg;
    GridView gvPicture;
    ImageView ivSoloPicture;
    ImageAdapter adapter = null;
    Button soloBack;
    Bundle backUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backUp = savedInstanceState;
        setContentView(R.layout.photo_gridview);
        
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        tvMsg.setText("Select a picture to show detail");
        thumbIds = new Integer[]{R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4, R.drawable.icon5, R.drawable.icon6, R.drawable.arrow_left, R.drawable.arrow_logo, R.drawable.cover_logo, R.drawable.fb_logo, R.drawable.linked_logo, R.drawable.logo_cinema, R.drawable.logo_yahoo, R.drawable.star_logo};
        gvPicture = (GridView) findViewById(R.id.gvPicture);
        // thiết lập DataSource cho Adapter
        adapter = new ImageAdapter(this, thumbIds);
        // gán Adapter vào GridView
        gvPicture.setAdapter(adapter);
        gvPicture.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // không mở Activity mới mà chỉ thiết lập lại Layout
        setContentView(R.layout.solo_picture);
        tvSoloMsg = (TextView) findViewById(R.id.tvSoloMsg);
        tvSoloMsg.setText("Image at " + position);
        ivSoloPicture = (ImageView) findViewById(R.id.ivSoloPicture);
        // thiết lập hình ảnh đang chọn lên ImageView mới
        ivSoloPicture.setImageResource(thumbIds[position]);
        soloBack = (Button) findViewById(R.id.soloBack);
        // thiết lập sự kiện để khôi phục lại MainActivity
        soloBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreate(backUp);
            }
        });
    }
}
