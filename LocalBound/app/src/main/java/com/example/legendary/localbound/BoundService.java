package com.example.legendary.localbound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Legendary on 11/04/2016.
 */
public class BoundService extends Service {
    private final IBinder myBinder=new MyLocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getCurrentTime(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
        return dateFormat.format(new Date());
    }

    public class MyLocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}
