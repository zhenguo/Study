package com.quxiangtech.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.quxiangtech.binder.common.Stub;

public class RemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    // 需要一个Stub ? BpBinder?
    IBinder iBinder = new Stub().asBinder();
}
