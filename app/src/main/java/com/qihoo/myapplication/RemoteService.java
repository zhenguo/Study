package com.qihoo.myapplication;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class RemoteService extends Service {
    private static final String TAG = "RemoteService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");
        bindService(new Intent(getApplicationContext(), MotionDetectorService.class), connection, BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {
        IMotionDector mMotionDetector;

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            try {
                if (mMotionDetector == null) {
                    mMotionDetector = IMotionDector.Stub.asInterface(service);
                }

                mMotionDetector.startDetect();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");

            try {
                if (mMotionDetector != null) {
                    mMotionDetector.stopDetect();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
}
