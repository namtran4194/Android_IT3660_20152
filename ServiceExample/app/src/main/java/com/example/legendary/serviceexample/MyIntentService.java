package com.example.legendary.serviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Legendary on 11/04/2016.
 */
public class MyIntentService extends IntentService {
    private static final String TAG = "ServiceExample";

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "Intent Service Started");
    }

    public MyIntentService() {
        super("MyIntentService");
    }
}
