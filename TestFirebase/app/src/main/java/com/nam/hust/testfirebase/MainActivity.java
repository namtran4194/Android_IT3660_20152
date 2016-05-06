package com.nam.hust.testfirebase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Firebase root;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        root = new Firebase("https://expenseproject.firebaseio.com/1fd2644b/Income");

        show = (TextView) findViewById(R.id.show);
        Query query = root.orderByValue();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = "";
                for (DataSnapshot Items : dataSnapshot.getChildren()) {
                    Item item = Items.getValue(Item.class);
                    s += item.getType() + " ";
                }
                show.setText(s);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                show.setText(firebaseError.getMessage());
            }
        });
    }
}
