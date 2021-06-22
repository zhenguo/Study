package com.qihoo.keeplive;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.os.Process;


import timber.log.Timber;

public class DaemonNative {
    public static ComponentName sCN;

    static {
        System.loadLibrary("clntv");
        DaemonNative.sCN = new ComponentName(nk.getInstance().getContext(), ViInstrumentation.class);
    }

    public DaemonNative() {
        super();
    }

    public static native void forkChild(String arg0, String arg1, String arg2, String arg3, String arg4);

    public static native int lockFile(String arg0);

    public static void restartProcess() {
//        boolean forkProcessSwitch = Config.forkProcessSwitch();
        boolean forkProcessSwitch = true;
        Timber.tag("maolei").d("restartProcess, 开关：" + forkProcessSwitch);
        Context v0 = nk.getInstance().getContext();
        if (v0 != null && forkProcessSwitch) {
            v0.startInstrumentation(DaemonNative.sCN, null, null);
        }
    }

    @SuppressLint(value = {"DiscouragedPrivateApi"})
    public static void setProcessName(String arg6) {
        try {
            Process.class.getDeclaredMethod("setArgV0", String.class).invoke(null, arg6);
        } catch (Exception e) {
            Timber.tag("maolei").e( "setProcessName failed");
        }
    }
}

