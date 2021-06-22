package com.qihoo.keeplive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import timber.log.Timber;

public abstract class BaseService extends Service {
    public BaseService() {
        super();
    }

    public String getMyName() {
        return this.getClass().getSimpleName();
    }

    @Nullable
    public IBinder onBind(Intent arg1) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Timber.tag("maolei").d(this.getMyName() + " oncreate");
    }

    public void onDestroy() {
        super.onDestroy();
        Timber.tag("maolei").d(this.getMyName() + " onDestroy");
    }

    public int onStartCommand(Intent arg1, int arg2, int arg3) {
        Timber.tag("maolei").d(this.getMyName() + " onStartCommand");
        return Service.START_STICKY;
    }

    public void onTaskRemoved(Intent arg1) {
        Timber.tag("maolei").d("onTaskRemoved");
    }
}

