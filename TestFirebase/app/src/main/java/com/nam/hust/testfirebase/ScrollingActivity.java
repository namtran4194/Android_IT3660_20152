package com.nam.hust.testfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class ScrollingActivity extends AppCompatActivity {
    Button send;
    EditText et;
    Firebase root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Firebase.setAndroidContext(this);
        root = new Firebase("https://expenseproject.firebaseio.com");

        send = (Button) findViewById(R.id.btnSend);
        et = (EditText) findViewById(R.id.text);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root.setValue(Integer.parseInt(et.getText().toString()));
            }
        });
    }
}
