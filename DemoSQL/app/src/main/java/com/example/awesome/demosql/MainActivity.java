package com.example.awesome.demosql;

import android.app.TabActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends TabActivity {
    Cursor model = null;
    RestaurantAdapter adapter = null;
    RestaurantHelper helper;
    EditText name = null;
    EditText address = null;
    RadioGroup types = null;
    EditText notes = null;
    private AdapterView.OnItemClickListener onListClick = new
            AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    model.moveToPosition(position);
                    name.setText(helper.getName(model));
                    address.setText(helper.getAddress(model));
                    notes.setText(helper.getNotes(model));
                    if (helper.getType(model).equals("sit-down")) {
                        types.check(R.id.sit_down);
                    } else if (helper.getType(model).equals("take-out")) {
                        types.check(R.id.take_out);
                    } else {
                        types.check(R.id.delivery);
                    }
                    getTabHost().setCurrentTab(1);
                }
            };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new RestaurantHelper(this);

        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.addr);
        types = (RadioGroup) findViewById(R.id.types);
        notes = (EditText) findViewById(R.id.notes);
        Button save = (Button) findViewById(R.id.save);

        ListView list_restaurant = (ListView) findViewById(R.id.restaurants);
        model = helper.getAll();
        startManagingCursor(model);
        adapter = new RestaurantAdapter(model);
        list_restaurant.setAdapter(adapter);
        list_restaurant.setOnItemClickListener(onListClick);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = null;
                switch (types.getCheckedRadioButtonId()) {
                    case R.id.take_out:
                        type = "take_out";
                        break;
                    case R.id.sit_down:
                        type = "sit_down";
                        break;
                    case R.id.delivery:
                        type = "delivery";
                        break;
                }
                helper.insert(name.getText().toString(), address.getText().toString(), type, notes.getText().toString());
                model.requery();
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

    static class RestaurantHolder {
        private TextView name = null;
        private TextView address = null;
        private ImageView icon = null;

        public RestaurantHolder(View row) {
            name = (TextView) row.findViewById(R.id.title);
            address = (TextView) row.findViewById(R.id.address);
            icon = (ImageView) row.findViewById(R.id.icon);
        }

        void populateFrom(Cursor c, RestaurantHelper helper) {
            name.setText(helper.getName(c));
            address.setText(helper.getAddress(c));
            if (helper.getType(c).equals("sit-down"))
                icon.setImageResource(R.drawable.ball_red);
            else if (helper.getType(c).equals("take-out"))
                icon.setImageResource(R.drawable.ball_yellow);
            else icon.setImageResource(R.drawable.ball_green);
        }
    }

    class RestaurantAdapter extends CursorAdapter {
        RestaurantAdapter(Cursor c) {
            super(MainActivity.this, c);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            RestaurantHolder holder = (RestaurantHolder) view.getTag();
            holder.populateFrom(cursor, helper);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            RestaurantHolder holder = new RestaurantHolder(row);
            row.setTag(holder);
            return row;
        }
    }
}
