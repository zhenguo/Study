package com.quxiangtech.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class MotionDetectorService extends Service {
    private static final String TAG = "MotionDetectorService";

    @Override
    public void onCreate() {
        super.onCreate();


        Log.d(TAG, "MotionDetectorService start");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMotionDetectorStub;
    }

    private final IMotionDector.Stub mMotionDetectorStub = new IMotionDector.Stub() {
        @Override
        public void startDetect() throws RemoteException {
            Log.d(TAG, "startDetect");
        }

        @Override
        public void stopDetect() throws RemoteException {
            Log.d(TAG, "stopDetect");
        }
    };
}
