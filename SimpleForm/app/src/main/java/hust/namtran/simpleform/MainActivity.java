package hust.namtran.simpleform;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    Restaurant r = new Restaurant();
    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            EditText name = (EditText) findViewById(R.id.name);
            EditText address = (EditText) findViewById(R.id.addr);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            Toast toast = Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT);
            toast.show();
            name.setText("");
            address.setText("");
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(onSave);
    }
}