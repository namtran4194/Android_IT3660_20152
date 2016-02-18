package hust.namtran.autocompletetextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    String[] source = {"lorem", "ipsum", "dolor", "sit", "amet"};
    TextView selection;
    AutoCompleteTextView autoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.selection);

        autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        autoComplete.addTextChangedListener(this);
        autoComplete.setAdapter(new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, source));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        selection.setText(autoComplete.getText());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
