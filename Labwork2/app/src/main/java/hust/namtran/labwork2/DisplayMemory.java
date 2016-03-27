package hust.namtran.labwork2;

/**
 * Created by NamTX on 14/03/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMemory extends Activity {
    SharedPreferences prefs;
    String SavedNameKey = "com.example.simplesave.SavedName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_display_memory);
        Intent intent = getIntent();
        prefs = this.getSharedPreferences("com.example.simplesave", Context.MODE_PRIVATE);
        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText("Data Retrieved from Shared Preferences:" + prefs.getString(SavedNameKey, " "));
        // Set the text view as the activity layout
        setContentView(textView);
    }
}
