package com.example.legendary.labwork;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Legendary on 06/04/2016.
 */
public class PlaceHolderFragment extends Fragment {
    private MainActivity main;

    public PlaceHolderFragment(MainActivity main) {
        this.main = main;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_database, container, false);
        View btnAdd = view.findViewById(R.id.button1);
        View btnLookup = view.findViewById(R.id.button2);
        View btnRemove = view.findViewById(R.id.button3);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newProduct();
            }
        });
        btnLookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lookupProduct();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeProduct();
            }
        });

        return view;
    }

    private void removeProduct() {
        main.removeProduct(getView());
    }

    private void lookupProduct() {
        main.lookupProduct(getView());
    }

    private void newProduct() {
        main.newProduct(getView());
    }
}
