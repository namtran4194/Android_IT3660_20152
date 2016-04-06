package com.example.legendary.labwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView idView;
    EditText etProductName;
    EditText etQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new PlaceHolderFragment(this)).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        idView = (TextView) findViewById(R.id.tvID);
        etProductName = (EditText) findViewById(R.id.etProductName);
        etQuantity = (EditText) findViewById(R.id.etQuantity);
    }

    public void newProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String productName = etProductName.getText().toString();
        String quantity = etQuantity.getText().toString();
        if (!productName.isEmpty()) {
            if (!quantity.isEmpty()) {
                Product product = new Product(productName, Integer.parseInt(quantity));
                dbHandler.addProduct(product);
            } else {
                Toast.makeText(this, "Product name cannot be empty", Toast.LENGTH_SHORT).show();
                etQuantity.requestFocus();
                return;
            }
        } else {
            Toast.makeText(this, "Quantity cannot be empty", Toast.LENGTH_SHORT).show();
            etProductName.requestFocus();
            return;
        }

        etProductName.setText("");
        etQuantity.setText("");
        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
    }

    public void lookupProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Product product = dbHandler.findProduct(etProductName.getText().toString());
        if (product != null) {
            idView.setText(String.valueOf(product.getId()));
            etQuantity.setText(String.valueOf(product.getQuantity()));
        } else
            idView.setText("No Match Found");
    }

    public void removeProduct(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        boolean result = dbHandler.deleteProduct(etProductName.getText().toString());
        if (result) {
            idView.setText("Record Deleted");
            etProductName.setText("");
            etQuantity.setText("");
        } else
            idView.setText("No Match Found");
    }
}
