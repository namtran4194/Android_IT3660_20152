package hust.namtran.basicwidgetsradiobutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener {
    RadioGroup orientation;
    RadioGroup position;
    RadioButton horizontal, vertical;
    RadioButton left, center, right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientation = (RadioGroup) findViewById(R.id.orientation);
        position = (RadioGroup) findViewById(R.id.position);
        horizontal = (RadioButton) findViewById(R.id.horizontal);
        vertical = (RadioButton) findViewById(R.id.vertical);
        left = (RadioButton) findViewById(R.id.left);
        center = (RadioButton) findViewById(R.id.center);
        right = (RadioButton) findViewById(R.id.right);

        horizontal.setOnCheckedChangeListener(this);
        vertical.setOnCheckedChangeListener(this);
        left.setOnCheckedChangeListener(this);
        center.setOnCheckedChangeListener(this);
        right.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView == horizontal) {
                orientation.setOrientation(LinearLayout.HORIZONTAL);
            }
            if (buttonView == vertical) {
                orientation.setOrientation(LinearLayout.VERTICAL);
            }
            if (buttonView == left) {
                position.setGravity(Gravity.LEFT);
            }
            if (buttonView == center) {
                position.setGravity(Gravity.CENTER);
            }
            if (buttonView == right) {
                position.setGravity(Gravity.RIGHT);
            }
        }
    }
}
