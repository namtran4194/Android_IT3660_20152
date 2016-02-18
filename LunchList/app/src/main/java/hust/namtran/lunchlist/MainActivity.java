package hust.namtran.lunchlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    List<Restaurant> model = new ArrayList<Restaurant>();
    ArrayAdapter<Restaurant> adapter = null;
    EditText name;
    EditText address;
    RadioGroup types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list_restaurant = (ListView) findViewById(R.id.list_restaurant);
        adapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, model);
        list_restaurant.setAdapter(adapter);
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        types = (RadioGroup) findViewById(R.id.types);
        Button save = (Button) findViewById(R.id.save);

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
                        restaurant.setType("Take-out");
                        break;
                    case R.id.sit_down:
                        restaurant.setType("Sit-down");
                        break;
                    case R.id.delivery:
                        restaurant.setType("Delivery");
                        break;
                }
                adapter.add(restaurant);
                name.setText("");
                address.setText("");
                Toast.makeText(MainActivity.this, "Saved restaurant's information\n" + "Name: " + res_name + "\nAddress: " + res_address + "\nType: " + restaurant.getType(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
