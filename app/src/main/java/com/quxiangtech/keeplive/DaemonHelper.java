package com.quxiangtech.keeplive;

import android.content.Context;
import android.util.Log;

import androidx.core.util.ObjectsCompat;

import com.byteww.llqql.library.base.BaseApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class DaemonHelper {
    //public static final String PROCESS_NAME = "com.byteww.llqql.library.clntv";

    public static final Map a = new HashMap();
    public static final Map b = new HashMap();
    public static final Map c = new HashMap();
    public static final Map d = new HashMap();
    public static final Map e = new HashMap();
    public static final Map f = new HashMap();
    public static final Map g = new HashMap();
    public static final List h = new ArrayList();

    public DaemonHelper() {
        super();
    }

    public static String a(Context arg0, String arg1, Map arg2) {
        if (arg1 != null) {
            Object v1 = arg2.get(arg1);
            File v0 = arg0.getFilesDir();
            if (v0 != null && v1 != null) {
                return new File(v0, ((String) v1)).getAbsolutePath();
            }

            return null;
        }

        throw new IllegalStateException("please init ProcessHolder first");
    }

    public static String a(Context arg1, Map arg2) {
        return DaemonHelper.a(arg1, ProcessHolder.PROCESS_NAME, arg2);
    }

    public static String getForkName() {
        String v0 = ProcessHolder.PROCESS_NAME;
        if (v0 != null) {
            return (String) DaemonHelper.g.get(v0);
        }

        throw new IllegalStateException("please init ProcessHolder first");
    }

    public static List getIndicatorFiles(Context arg4) {
        ArrayList v0 = new ArrayList();
        Iterator v1 = DaemonHelper.h.iterator();
        while (v1.hasNext()) {
            ((List) v0).add(DaemonHelper.a(arg4, (String) v1.next(), DaemonHelper.d));
        }

        return ((List) v0);
    }

    public static String getSelfForkIndicatorFile(Context arg1) {
        return DaemonHelper.a(arg1, DaemonHelper.d);
    }

    public static String getSelfForkLockFile(Context arg1) {
        return DaemonHelper.a(arg1, DaemonHelper.b);
    }

    public static String getSelfForkWaitFile(Context arg1) {
        return DaemonHelper.a(arg1, DaemonHelper.c);
    }

    public static String getSelfForkWaitIndicatorFile(Context arg1) {
        return DaemonHelper.a(arg1, DaemonHelper.e);
    }

    public static void init(Context arg8) {
        String v8 = arg8.getPackageName();
        DaemonHelper.h.add(v8);
        List v0 = DaemonHelper.h;
        v0.add(v8 + ":resident");
        DaemonHelper.g.put(v8, "main");
        DaemonHelper.a.put(v8, "main");
        DaemonHelper.b.put(v8, "main_c");
        DaemonHelper.c.put(v8, "resident_c");
        DaemonHelper.d.put(v8, "main_indicator");
        DaemonHelper.e.put(v8, "resident_indicator");
        String v0_2 = v8 + ":resident";
        DaemonHelper.g.put(v0_2, "resident");
        DaemonHelper.a.put(v0_2, "resident");
        DaemonHelper.b.put(v0_2, "resident_c");
        DaemonHelper.c.put(v0_2, "main_c");
        DaemonHelper.d.put(v0_2, "resident_indicator");
        DaemonHelper.e.put(v0_2, "main_indicator");
        Map v0_3 = DaemonHelper.f;
        try {
            v0_3.put(v8 + ":resident", Class.forName("com.byteww.llqql.resident.ResidentService"));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void startServices(Context arg2) {
        Iterator v0 = DaemonHelper.f.values().iterator();
        while (v0.hasNext()) {
            final Class clazz = (Class) v0.next();
            if(BaseApplication.sIsDebug) {
                Log.d("test","测试启动Service" + clazz.getSimpleName());
            }
            if(ObjectsCompat.equals(clazz.getSimpleName(), "ResidentService")) {
                ServiceUtils.startForegroundService(arg2, clazz);
            }else {
                ServiceUtils.startService(arg2, clazz);
            }
        }
    }
}