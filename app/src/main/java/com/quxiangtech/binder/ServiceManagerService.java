package com.quxiangtech.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ServiceManagerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceManager();
    }
}
