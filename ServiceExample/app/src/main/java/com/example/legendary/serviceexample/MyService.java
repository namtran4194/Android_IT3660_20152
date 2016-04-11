package com.example.legendary.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Legendary on 11/04/2016.
 */
public class MyService extends Service {
    private static final String TAG = ".ServiceExample";

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service onStartCommand");

        final int currentId = startId;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    long endTime = System.currentTimeMillis() + 10 * 1000;
                    while (System.currentTimeMillis() < endTime) {
                        synchronized (this) {
                            try {
                                wait(endTime - System.currentTimeMillis());
                            } catch (Exception e) {

                            }
                        }
                    }
                    Log.i(TAG, "Service running" + currentId);
                }
                stopSelf();
            }
        };
        Thread t = new Thread(r);
        t.start();
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Service onDestroy");
    }
}
