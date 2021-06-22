package com.qihoo.binder.localBinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class LocalService extends Service {
    private final IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public LocalService getService() {
            return LocalService.this;
        }
    }

    public void testLocalBinder() {
        System.out.println("local binder test successful");
    }
}
