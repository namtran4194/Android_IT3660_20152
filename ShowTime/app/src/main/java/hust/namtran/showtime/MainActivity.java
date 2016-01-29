package hust.namtran.showtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnDate;
    TextView time;
    // Use of an anonymous inner class
    private View.OnClickListener btProcess = new View.OnClickListener() {
        public void onClick(View v) {
            time = (TextView) findViewById(R.id.text);
            time.setText(new Date().toString());
            Toast.makeText(MainActivity.this, "Option 2: Use of an anonymous inner class", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnDate = (Button) findViewById(R.id.btnDate);
        time = (TextView) findViewById(R.id.text);

        // Creating new Listener class
//        btnDate.setOnClickListener(new ButtonListener());

        // Use of an anonymous inner class
//        btnDate.setOnClickListener(btProcess);

        // Use of an anonymous inner class
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText(new Date().toString());
                Toast.makeText(MainActivity.this, "Option 3: Use of an anonymous inner class", Toast.LENGTH_LONG).show();
            }
        });
    }

    class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            time = (TextView) findViewById(R.id.text);
            time.setText(new Date().toString());
            Toast.makeText(MainActivity.this, "Option 1: Creating new Listener class", Toast.LENGTH_LONG).show();
        }
    }
}