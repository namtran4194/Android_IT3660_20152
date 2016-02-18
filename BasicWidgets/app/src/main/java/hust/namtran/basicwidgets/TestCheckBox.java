package hust.namtran.basicwidgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by NamTX on 02/02/2016.
 */
public class TestCheckBox extends Activity {
    TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkbox);

        CheckBox checkBox = (CheckBox) findViewById(R.id.cbox);
        textView = (TextView) findViewById(R.id.cbText);
        textView.setText(" N/A");

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView.setText(" checked");
                } else {
                    textView.setText(" unchecked");
                }
            }
        });
        Button btnBack = (Button) findViewById(R.id.cbBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
