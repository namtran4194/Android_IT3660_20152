package hust.namtran.lifeandtimes;

import android.app.TabActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TabActivity {
    List<Restaurant> model = new ArrayList<Restaurant>();
    RestaurantAdapter adapter = null;
    Restaurant current = null;
    EditText name;
    EditText address;
    RadioGroup types;
    EditText notes;
    int progress;
    private AdapterView.OnItemClickListener onListClick = new
            AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    current = model.get(position);
                    name.setText(current.getName());
                    address.setText(current.getAddress());
                    if (current.getType().equals("sit-down")) {
                        types.check(R.id.sit_down);
                    } else if (current.getType().equals("take-out")) {
                        types.check(R.id.take_out);
                    } else {
                        types.check(R.id.delivery);
                    }
                    getTabHost().setCurrentTab(1);
                }
            };
    private Runnable longTask = new Runnable() {
        public void run() {
            for (int i = progress; i < 10000; i+=200) {
                doSomeLongWork(200);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);

        ListView list_restaurant = (ListView) findViewById(R.id.restaurants);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.addr);
        types = (RadioGroup) findViewById(R.id.types);
        notes = (EditText) findViewById(R.id.notes);
        Button save = (Button) findViewById(R.id.save);
        adapter = new RestaurantAdapter();
        list_restaurant.setAdapter(adapter);
        list_restaurant.setOnItemClickListener(onListClick);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = new Restaurant();
                String res_name = name.getText().toString();
                String res_address = address.getText().toString();

                if (!res_name.isEmpty()) {
                    current.setName(res_name);
                    if (!res_address.isEmpty())
                        current.setAddress(res_address);
                    else {
                        Toast.makeText(MainActivity.this, "Please enter address", Toast.LENGTH_SHORT).show();
                        address.requestFocus();
                        return;
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                    return;
                }
                switch (types.getCheckedRadioButtonId()) {
                    case R.id.take_out:
                        current.setType("take-out");
                        break;
                    case R.id.sit_down:
                        current.setType("sit-down");
                        break;
                    case R.id.delivery:
                        current.setType("delivery");
                        break;
                }
                current.setNotes(notes.getText().toString());
                adapter.add(current);
                name.setText("");
                address.setText("");
                notes.setText("");
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List", getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);
        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details", getResources().getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);
        getTabHost().setCurrentTab(0);
    }

    private void doSomeLongWork(final int incr) {
        runOnUiThread(new Runnable() {
            public void run() {
                progress += incr;
                setProgress(progress);
            }
        });
        SystemClock.sleep(250); // should be something more useful!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toast) {
            String message = "No restaurant selected";
            if (current != null)
                message = current.getNotes();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.run) {
            setProgressBarVisibility(true);
            progress = 0;
            new Thread(longTask).start();
            return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    static class RestaurantHolder {
        private TextView name = null;
        private TextView address = null;
        private ImageView icon = null;

        public RestaurantHolder(View row) {
            name = (TextView) row.findViewById(R.id.title);
            address = (TextView) row.findViewById(R.id.address);
            icon = (ImageView) row.findViewById(R.id.icon);
        }

        void populateFrom(Restaurant restaurant) {
            name.setText(restaurant.getName());
            address.setText(restaurant.getAddress());
            if (restaurant.getType().equals("sit-down"))
                icon.setImageResource(R.drawable.ball_red);
            else if (restaurant.getType().equals("take-out"))
                icon.setImageResource(R.drawable.ball_yellow);
            else icon.setImageResource(R.drawable.ball_green);
        }
    }

    class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        RestaurantAdapter() {
            super(MainActivity.this, android.R.layout.simple_list_item_1, model);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            RestaurantHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, parent, false);
                holder = new RestaurantHolder(row);
                row.setTag(holder);
            } else holder = (RestaurantHolder) row.getTag();
            holder.populateFrom(model.get(position));
            return row;
        }
    }
}
