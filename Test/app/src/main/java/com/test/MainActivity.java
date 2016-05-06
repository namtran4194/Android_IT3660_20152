package com.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView listItem, items;
    TextView date, total;
    TextView type, cost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        listItem = (ListView) findViewById(R.id.list_all);
        items = (ListView) findViewById(R.id.items);
        date = (TextView) findViewById(R.id.date);
        total = (TextView) findViewById(R.id.total_money);
        type = (TextView) findViewById(R.id.type);
        cost = (TextView) findViewById(R.id.cost);


    }
}