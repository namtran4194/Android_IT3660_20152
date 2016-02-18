package hust.namtran.datetimepickerdialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by NamTX on 05/02/2016.
 */
public class JobInWeek {
    private String title;
    private String description;
    private Date dateFinish;
    private Date timeFinish;

    public JobInWeek(String title) {
        super();
    }

    public JobInWeek(String title, String description, Date dateFinish, Date timeFinish) {
        super();
        this.title = title;
        this.description = description;
        this.dateFinish = dateFinish;
        this.timeFinish = timeFinish;
    }

    public String getDateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public String getHourFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    @Override
    public String toString() {
        return this.title + " | " + getDateFormat(this.dateFinish) + " | " + getHourFormat(this.timeFinish);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(Date timeFinish) {
        this.timeFinish = timeFinish;
    }
}
