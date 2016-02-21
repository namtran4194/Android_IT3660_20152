package hust.namtran.customlistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    List<Restaurant> model = new ArrayList<Restaurant>();
    RestaurantAdapter adapter = null;
    EditText name;
    EditText address;
    RadioGroup types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list_restaurant = (ListView) findViewById(R.id.list_restaurant);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        types = (RadioGroup) findViewById(R.id.types);
        Button save = (Button) findViewById(R.id.save);
        adapter = new RestaurantAdapter();
        list_restaurant.setAdapter(adapter);

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
