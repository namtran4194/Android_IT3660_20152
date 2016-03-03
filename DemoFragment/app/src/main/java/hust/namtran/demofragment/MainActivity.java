package hust.namtran.demofragment;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity implements MyListFragment.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRssItemSelected(String link) {
        MyFragment fragment = (MyFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
        }
    }

    @Override
    public void capNhatThongTin(String link) {
        MyFragment fragment = (MyFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setInfo(link);
        }

    }

    @Override
    public void clearAllInfo() {
        MyFragment fragment = (MyFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.clear();
        }
    }
}
