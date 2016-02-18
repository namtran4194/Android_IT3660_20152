package hust.namtran.basicwidgets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button editText = (Button) findViewById(R.id.editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent etIntent = new Intent(MainActivity.this, TestEditText.class);
                startActivity(etIntent);
            }
        });

        Button checkbox = (Button) findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cbIntent = new Intent(MainActivity.this, TestCheckBox.class);
                startActivity(cbIntent);
            }
        });

        Button radioButton = (Button) findViewById(R.id.btnRadio);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rdIntent = new Intent(MainActivity.this, TestRadioButton.class);
                startActivity(rdIntent);
            }
        });

    }
}
