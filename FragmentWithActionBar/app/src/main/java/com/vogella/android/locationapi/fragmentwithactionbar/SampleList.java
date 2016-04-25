package com.vogella.android.locationapi.fragmentwithactionbar;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Legendary on 25/04/2016.
 */
public class SampleList extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            SampleListFragment list = new SampleListFragment();
            fm.beginTransaction().add(android.R.id.content, list).commit();
        }
    }

    public static class SampleListFragment extends ListFragment {
        String[] numbers_text = new String[]{"one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine", "ten", "eleven",
                "twelve", "thirteen", "fourteen", "fifteen"};
        String[] numbers_digits = new String[]{"1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12", "13", "14", "15"};

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Toast.makeText(getActivity(), numbers_digits[(int) id], Toast.LENGTH_SHORT).show();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, numbers_text);
            setListAdapter(adapter);
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
}
