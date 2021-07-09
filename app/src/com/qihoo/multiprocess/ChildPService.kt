package com.qihoo.multiprocess

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log

private const val TAG = "ChildPService"

class ChildPService : Service() {
    // 这种Service如果自己被kill掉了，就无法拉起主进程了
    // 可以试试前台Service，或者后台播静默音乐
    private var mainBinder: IMultiSync? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        bindService(Intent(this, MPBinderProvider::class.java),
                object : ServiceConnection {
                    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                        mainBinder = IMultiSync.Stub.asInterface(service)
                        Log.d(TAG, "connect to main successful")
                    }

                    override fun onServiceDisconnected(name: ComponentName?) {
                        // main died, restart main immediately
                        Log.d(TAG, "main died, restart main immediately")
                        mainBinder = null
                    }
                }, BIND_AUTO_CREATE)
    }
}