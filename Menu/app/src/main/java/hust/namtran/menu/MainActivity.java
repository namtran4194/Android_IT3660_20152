package hust.namtran.menu;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    EditText name;
    EditText address;
    RadioGroup types;
    private AdapterView.OnItemClickListener onListClick = new
            AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Restaurant r = model.get(position);
                    name.setText(r.getName());
                    address.setText(r.getAddress());
                    if (r.getType().equals("sit_down")) {
                        types.check(R.id.sit_down);
                    } else if (r.getType().equals("take_out")) {
                        types.check(R.id.take_out);
                    } else {
                        types.check(R.id.delivery);
                    }
                    getTabHost().setCurrentTab(1);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list_restaurant = (ListView) findViewById(R.id.restaurants);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.addr);
        types = (RadioGroup) findViewById(R.id.types);
        Button save = (Button) findViewById(R.id.save);
        adapter = new RestaurantAdapter();
        list_restaurant.setAdapter(adapter);
        list_restaurant.setOnItemClickListener(onListClick);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurant restaurant = new Restaurant();
                String res_name = name.getText().toString();
                String res_address = address.getText().toString();

                if (!res_name.isEmpty()) {
                    restaurant.setName(res_name);
                    if (!res_address.isEmpty())
                        restaurant.setAddress(res_address);
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
                        restaurant.setType("take-out");
                        break;
                    case R.id.sit_down:
                        restaurant.setType("sit-down");
                        break;
                    case R.id.delivery:
                        restaurant.setType("delivery");
                        break;
                }
                adapter.add(restaurant);
                name.setText("");
                address.setText("");
                Toast.makeText(MainActivity.this, "Saved restaurant's information\n" + "Name: " + res_name + "\nAddress: " + res_address + "\nType: " + restaurant.getType(), Toast.LENGTH_SHORT).show();
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

