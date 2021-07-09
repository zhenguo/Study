package com.qihoo.multiprocess

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.qihoo.multiprocess.IMultiSync.Stub

private const val TAG = "MPBinderProvider"

class MPBinderProvider : Service() {

    override fun onBind(intent: Intent?): IBinder {
        return syncBinder
    }

    private val syncBinder: Stub = object : Stub() {
        override fun doSyncPeriod(period: Long) {
            Log.d(TAG, "remote call doSyncPeriod: ")
        }
    }
}