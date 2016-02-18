package hust.namtran.datetimepickerdialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView tvDate, tvTime;
    EditText editJob, editContent;
    Button btnDate, btnTime, btnAdd;
    // khai báo Datasource lưu trữ danh sách công việc
    ArrayList<JobInWeek> listJob = new ArrayList<JobInWeek>();
    // khai báo ArrayAdapter cho Listview
    ArrayAdapter<JobInWeek> adapter = null;
    ListView list;
    Calendar calendar;
    Date dateFinish, timeFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets();
        getDefaultInfo();
        addEventFormWidgets();
    }

    /*
    * load các control theo Id
    */
    private void getFormWidgets() {
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        editJob = (EditText) findViewById(R.id.edit_job);
        editContent = (EditText) findViewById(R.id.edit_content);
        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);
        btnAdd = (Button) findViewById(R.id.btnAddJob);
        list = (ListView) findViewById(R.id.list_job);
        // gán Datasource vào ArrayAdapter
        adapter = new ArrayAdapter<JobInWeek>(this, android.R.layout.simple_list_item_1, listJob);
        // gán ArrayAdapter vào Listview
        list.setAdapter(adapter);
    }

    /**
     * hàm lấy thông số mặc định khi lần đầu chạy ứng dụng
     */
    private void getDefaultInfo() {
        // lấy ngày hiện tại của hệ thống
        calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = null;
        // định dạng ngày/tháng/năm
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateFormat = simpleDateFormat.format(calendar.getTime());
        // hiển thị lên giao diện
        tvDate.setText(dateFormat);
        // dịnh dạng giờ phút am/pm
        simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String hourFormat = simpleDateFormat.format(calendar.getTime());
        // hiển thị lên giao diện
        tvTime.setText(hourFormat);
        // lấy giờ theo 24h để lập trình theo Tag
        simpleDateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());
        tvTime.setTag(simpleDateFormat.format(calendar.getTime()));
        editJob.requestFocus();
        // gán calendar.getTime() cho ngày và giờ hoàn thành
        dateFinish = calendar.getTime();
        timeFinish = calendar.getTime();
    }

    /**
     * hàm gán các sự kiện cho các control
     */
    private void addEventFormWidgets() {
        btnDate.setOnClickListener(new ButtonEvent());
        btnTime.setOnClickListener(new ButtonEvent());
        btnAdd.setOnClickListener(new ButtonEvent());

        list.setOnItemClickListener(new ListViewEvent());
        list.setOnItemLongClickListener(new ListViewEvent());
    }

    /**
     * hàm hiển thị DatePicker dialog
     */
    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // mỗi lần thay đổi ngày/tháng/năm thì cập nhật lại TextView Date
                tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                calendar.set(year, monthOfYear, dayOfMonth);
                dateFinish = calendar.getTime();
            }
        };
        // các lệnh sau xử lý ngày giờ trong DatePickerDialog
        String s = tvDate.getText().toString();
        String[] temp = s.split("/");
        int ngay = Integer.parseInt(temp[0]);
        int thang = Integer.parseInt(temp[1]);
        int nam = Integer.parseInt(temp[2]);
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, callback, nam, thang, ngay);
        datePickerDialog.setTitle("Chọn ngày hoàn thành");
        datePickerDialog.show();
    }

    /**
     * hàm hiển thị TimePicker dialog
     */
    private void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // xử lý lưu giờ và AM, PM
                String s = hourOfDay + ":" + minute;
                int tempHour = hourOfDay;
                if (tempHour > 12)
                    tempHour = tempHour - 12;
                tvTime.setText(tempHour + ":" + minute + (hourOfDay > 12 ? " PM" : " AM"));
                // lưu giờ thực vào Tag
                tvTime.setTag(s);
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                timeFinish = calendar.getTime();
            }
        };
        // các lệnh sau xử lý ngày giờ trong TimePickerDialog
        String s = tvTime.getTag().toString();
        String[] temp = s.split(":");
        int gio = Integer.parseInt(temp[0]);
        int phut = Integer.parseInt(temp[1]);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, callback, gio, phut, true);
        timePickerDialog.setTitle("Chọn giờ hoàn thành");
        timePickerDialog.show();
    }

    /**
     * hàm xử lý đưa công việc vào ListView khi nhấn nút Add Job
     */
    private void processAddJob() {
        String title = editJob.getText().toString();
        String description = editContent.getText().toString();
        if (title.isEmpty())
            title = "untitle";
        if (description.isEmpty())
            description = "no description";
        JobInWeek job = new JobInWeek(title, description, dateFinish, timeFinish);
        listJob.add(job);
        Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        // sau khi cập nhật dữ liệu thì reset EditText và focus tới editJob
        editJob.setText("");
        editContent.setText("");
        editJob.requestFocus();
    }

    /**
     * class xử lý các sự kiện của các Button
     */
    private class ButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnDate:
                    showDatePickerDialog();
                    break;
                case R.id.btnTime:
                    showTimePickerDialog();
                    break;
                case R.id.btnAddJob:
                    processAddJob();
                    break;
            }
        }
    }

    /**
     * class xử lý sự kiện của ListView
     */
    private class ListViewEvent implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // hiển thị nội dung công việc tại vị trí thứ position
            Toast.makeText(MainActivity.this, listJob.get(position).getDescription(), Toast.LENGTH_LONG).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // xoá vị trí thứ position
            listJob.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
