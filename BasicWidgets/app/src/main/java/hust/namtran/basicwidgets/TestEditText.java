package hust.namtran.basicwidgets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TestEditText extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text);

        EditText editText = (EditText) findViewById(R.id.text);
        editText.setText("Licensed under the Apache License, Version 2.0 (the License).");

        Button btnBack = (Button) findViewById(R.id.etBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
