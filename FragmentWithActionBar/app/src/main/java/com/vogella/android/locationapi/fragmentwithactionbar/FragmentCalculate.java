package com.vogella.android.locationapi.fragmentwithactionbar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Legendary on 25/04/2016.
 */
public class FragmentCalculate extends Activity {
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Fragment newFragment = SimpleAddition.newInstance(i);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.FramLayout1, newFragment).commit();
        } else {
            i = savedInstanceState.getInt("level");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", i);
    }

    public static class SimpleAddition extends Fragment {
        int mNum;

        static SimpleAddition newInstance(int num) {
            SimpleAddition f = new SimpleAddition();
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.addition, container, false);

            Button b = (Button) v.findViewById(R.id.button1);
            final EditText et1 = (EditText) v.findViewById(R.id.editText1);
            final EditText et2 = (EditText) v.findViewById(R.id.editText2);
            final TextView tv = (TextView) v.findViewById(R.id.textView1);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int a = Integer.parseInt(et1.getText().toString());
                    int b = Integer.parseInt(et2.getText().toString());
                    tv.setText("Total = " + String.valueOf(a + b));
                }
            });
            return v;
        }
    }
}
