package com.quxiangtech.keeplive;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import timber.log.Timber;

public class ServiceUtils {
    public ServiceUtils() {
        super();
    }

    public static void startService(Context arg1, Class arg2) {
        Intent v0 = new Intent(arg1, arg2);
        try {
            arg1.startService(v0);
        }
        catch(Throwable t) {
            Timber.tag("maolei").e("startService error,clz=" + arg2.getSimpleName());
        }
    }

    public static void startForegroundService(Context arg1, Class arg2) {
        Intent v0 = new Intent(arg1, arg2);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                arg1.startForegroundService(v0);
            }else {
                arg1.startService(v0);
            }
        }
        catch(Throwable t) {
            Timber.tag("maolei").e("startService error,clz=" + arg2.getSimpleName());
        }
    }
}

