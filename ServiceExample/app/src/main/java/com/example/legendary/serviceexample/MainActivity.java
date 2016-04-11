package com.example.legendary.serviceexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    public void buttonClick(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }
}
