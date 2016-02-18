package hust.namtran.gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by NamTX on 04/02/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private Integer[] thumbIds;

    public ImageAdapter(Context context, Integer[] thumbIds) {
        this.context = context;
        this.thumbIds = thumbIds;
    }

    @Override
    public int getCount() {
        return thumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgView;
        if (convertView == null) {
            imgView = new ImageView(context);
            imgView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgView.setPadding(5, 5, 5, 5);
        } else {
            imgView = (ImageView) convertView;
        }
        imgView.setImageResource(thumbIds[position]);
        return imgView;
    }
}
