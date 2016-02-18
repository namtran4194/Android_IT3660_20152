package hust.namtran.demogallery;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    Integer[] thumbIds = new Integer[]{R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4, R.drawable.icon5, R.drawable.icon6, R.drawable.arrow_left, R.drawable.arrow_logo, R.drawable.cover_logo, R.drawable.fb_logo, R.drawable.linked_logo, R.drawable.logo_cinema, R.drawable.logo_yahoo, R.drawable.star_logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "picture " + (position + 1) + " selected", Toast.LENGTH_SHORT).show();
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageResource(thumbIds[position]);
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int imageBackground;

        public ImageAdapter(Context context) {
            this.context = context;
            TypedArray typedArray = context.obtainStyledAttributes(R.styleable.MyGallery);
            imageBackground = typedArray.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
            typedArray.recycle();
        }

        @Override
        public int getCount() {
            return thumbIds.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(thumbIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
            imageView.setBackgroundResource(imageBackground);
            return imageView;
        }
    }

}
