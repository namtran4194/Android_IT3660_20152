package hust.namtran.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by NamTX on 04/02/2016.
 */
public class ShowText extends AppCompatActivity {
    private String[] items = {"Ipad", "Iphone", "Samsung", "Nokia", "Sony Ericson", "LG", "Q-Mobile", "HTC", "Blackberry", "G-Phone", "FPT Phone", "HK Phone"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_gridview);

        GridView gvText = (GridView) findViewById(R.id.gvText);
        gvText.setAdapter(new ArrayAdapter<>(this, R.layout.cell, items));
        gvText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            TextView selection = (TextView) findViewById(R.id.selection);

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selection.setText("Selected item: " + items[position]);
            }
        });
    }
}
