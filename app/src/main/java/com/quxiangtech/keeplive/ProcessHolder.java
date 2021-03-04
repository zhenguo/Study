package com.quxiangtech.keeplive;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import java.util.Iterator;

import timber.log.Timber;

public class ProcessHolder {
    public static boolean IS_DAEMON = false;
    public static boolean IS_MAIN = false;
    public static boolean IS_SERVICE = false;
    public static String PROCESS_NAME = null;
    public static boolean a = false;


    public ProcessHolder() {
        super();
    }

    // 找到当前进程名称
    public static String a(Context arg4) {
        Object v2;
        int v0 = Process.myPid();
        Object v4 = arg4.getSystemService(Context.ACTIVITY_SERVICE);
        String v1 = null;
        if(v4 == null) {
            return v1;
        }

        Iterator v4_1 = ((ActivityManager)v4).getRunningAppProcesses().iterator();
        do {
            if(!v4_1.hasNext()) {
                return v1;
            }

            v2 = v4_1.next();
        }
        while(((ActivityManager.RunningAppProcessInfo)v2).pid != v0);

        return ((ActivityManager.RunningAppProcessInfo)v2).processName;
    }

    public static void init(Context arg4) {
        StringBuilder v4;
        if(ProcessHolder.a) {
            return;
        }

        String v0 = ProcessHolder.a(arg4);
        Timber.tag("maolei").d("processname = " + v0);
        String v1 = arg4.getPackageName();
        ProcessHolder.PROCESS_NAME = v0;
        if(arg4.getPackageName().equals(v0)) {
            // 主进程
            ProcessHolder.IS_MAIN = true;
        }
        else {
            if(v0 != null) {
                v4 = new StringBuilder();
                v4.append(v1);
                v4.append(":resident");
                if(v0.equals(v4.toString())) {
                    ProcessHolder.IS_DAEMON = true;
                }
            }

            if(v0 != null) {
                v4 = new StringBuilder();
                v4.append(v1);
                v4.append(":service");
                if(v0.equals(v4.toString())) {
                    ProcessHolder.IS_SERVICE = true;
                }
            }
        }

        ProcessHolder.a = true; // init只运行一次
    }
}

