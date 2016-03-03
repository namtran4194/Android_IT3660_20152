package hust.namtran.demofragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by NamTX on 26/02/2016.
 */
public class MyListFragment extends Fragment {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button button = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        Button button3 = (Button) view.findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateInfo();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInfo();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " phải implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public void updateDetail() {
        //String newTime = String.valueOf(System.currentTimeMillis());
        String newTime = sdf.format(cal.getTime());
        listener.onRssItemSelected(newTime);
    }

    public void updateInfo() {
        listener.capNhatThongTin("Hướng dẫn: thanhlong90.it");
    }

    public void clearInfo() {
        listener.clearAllInfo();
    }

    public interface OnItemSelectedListener {
        public void onRssItemSelected(String link);

        public void capNhatThongTin(String link);

        public void clearAllInfo();
    }
}
