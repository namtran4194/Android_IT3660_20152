package hust.namtran.demofragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by NamTX on 26/02/2016.
 */
public class MyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        return view;
    }

    public void setText(String item) {
        TextView time = (TextView) getView().findViewById(R.id.detailsText);
        time.setText(item);
    }

    public void setInfo(String item) {
        TextView info = (TextView) getView().findViewById(R.id.infoText);
        info.setText(item);
    }

    public void clear() {
        TextView time = (TextView) getView().findViewById(R.id.detailsText);
        time.setText("yyyy-MM-dd HH:mm:ss");
        TextView info = (TextView) getView().findViewById(R.id.infoText);
        info.setText("...");
    }
}
