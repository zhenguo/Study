package com.quxiangtech.binder.localBinder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.myapplication.R;

public class BindingActivity extends AppCompatActivity {
    private LocalService mLocalService;
    private boolean mBound;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalService.LocalBinder localBinder = (LocalService.LocalBinder) service;
            mLocalService = localBinder.getService();

            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_binder);
        bindService(new Intent(this, LocalService.class), conn, BIND_AUTO_CREATE);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();

        if (mBound) {
            mLocalService.testLocalBinder();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        unbindService(conn);
        mBound = false;
    }
}
