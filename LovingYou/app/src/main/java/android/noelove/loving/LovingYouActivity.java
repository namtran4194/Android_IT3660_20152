package android.noelove.loving;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import java.util.ArrayList;

public class LovingYouActivity extends Activity implements Runnable {
    /**
     * Called when the activity is first created.
     */
    private ExpandableListViewAdapter elvAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView1);
        listView.set
        listView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4) {
                Toast.makeText(getBaseContext(), "Child clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        listView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(getBaseContext(), "Group clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        elvAdapter = new ExpandableListViewAdapter(this, new ArrayList<String>(), new ArrayList<ArrayList<Love>>());
        listView.setAdapter(elvAdapter);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        final int ITEMS = 15;
        int count = 0;
        while (count != ITEMS) {
            count++;
            elvAdapter.addItem(DataProvider.getRandomLove("Love no. " + count));

            // Notify the adapter
            handler.sendEmptyMessage(1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            elvAdapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }

    };
}