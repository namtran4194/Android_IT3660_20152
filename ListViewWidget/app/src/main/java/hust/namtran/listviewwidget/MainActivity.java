package hust.namtran.listviewwidget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    String[] items = {"lorem", "ipsum", "dolor", "sit", "amet", "consectetuer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
        ListView list = (ListView) findViewById(R.id.list_item);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView selection = (TextView) findViewById(R.id.selection);
                selection.setText(items[position]);
            }
        });
    }
}
