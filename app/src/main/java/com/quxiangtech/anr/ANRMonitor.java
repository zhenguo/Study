package com.quxiangtech.anr;

import android.os.FileObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;

public class ANRMonitor extends FileObserver {
    private static ANRMonitor sInstance = new ANRMonitor();

    public static ANRMonitor getInstance() {
        if (sInstance == null) {
            synchronized (ANRMonitor.class) {
                if (sInstance == null) {
                    sInstance = new ANRMonitor();
                }
            }
        }

        return sInstance;
    }
    public ANRMonitor() {
        super(new File("/data/anr/"));
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        System.out.println("event: " + event + " path: " + path);
    }
}
