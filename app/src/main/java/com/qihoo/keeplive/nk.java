package com.qihoo.keeplive;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
//import com.vi.daemon.DaemonJobService;
//import com.vi.daemon.DaemonLog;
//import com.vi.daemon.screenmonitor.ScreenMonitorHelper;
//import com.byteww.llqql.library.Config;
//import com.byteww.llqql.library.authguide.romadapter.utils.RomUtils;

import java.io.File;
import java.util.Iterator;

import timber.log.Timber;

public class nk {
    public class nka implements Runnable {
        public nka(nk arg1) {
            this.a = arg1;
        }

        public void run() {
            nk.a(this.a, nk.a(this.a));
        }

        public nk a;
    }

    public class nkb implements Runnable {
        public nkb(nk arg1) {
            this.a = arg1;
        }

        public void run() {
            nk.b(this.a);
        }

        public nk a;
    }

    public class nke implements Runnable {
        public class nkea implements Runnable {
            public nkea() {

            }

            public void run() {
                DaemonNative.restartProcess();
            }
            private nke a;
        }

        public nke() {

        }

        public void run() {
            Process.setThreadPriority(-2);
            Timber.tag("maolei").d("startPackageMonitor");
            while (true) {
                try {
                    if ((nk.getInstance().getContext().getPackageManager().getApplicationInfo(nk.getInstance().getContext().getPackageName(), PackageManager.GET_META_DATA).flags & 2097152) != 0) {
                        DaemonNative.restartProcess();
                        for (int i = 0; i < 3; i++) {
                            new Thread(new nkea()).start();
                        }
                    }
                } catch (Throwable th) {
                    Timber.tag("maolei").e("getApplicationInfo error:%s", th.getMessage());
                }
            }
        }
    }


    public static nk a = new nk(null);

    //public mk a;
    public Context b;
    //public ok c;
    //public b d;

    public nk() {
        super();
        //this.d = new nk$d(this);
    }

    public nk(nka arg1) {
        this();
    }

    public static Context a(nk arg0) {
        return arg0.b;
    }

    private void a() {
        //new Thread(new nk$c(this)).start();
    }

    private void a(Context arg7) {
        Timber.tag("maolei").d("forkChild,context=" + arg7);

        String v0_1 = DaemonHelper.getForkName();
        String v1 = DaemonHelper.getSelfForkLockFile(arg7);
        String v2 = DaemonHelper.getSelfForkWaitFile(arg7);
        String v3 = DaemonHelper.getSelfForkIndicatorFile(arg7);
        String v7 = DaemonHelper.getSelfForkWaitIndicatorFile(arg7);
        Timber.tag("maolei").d("===============forkChild log start ==============");
        Timber.tag("maolei").d("forkChild,forkName=" + v0_1);
        Timber.tag("maolei").d("forkChild,forkLockFile=" + v1);
        Timber.tag("maolei").d("forkChild,forkWaitFile=" + v2);
        Timber.tag("maolei").d("forkChild,forkIndicatorFile=" + v3);
        Timber.tag("maolei").d("forkChild,forkWaitIndicatorFile=" + v7);
        Timber.tag("maolei").d("===============forkChild log end==============");

//        if(Config.forkProcessSwitch()) {
//            DaemonNative.forkChild(v0_1, v1, v2, v3, v7);
//        }
    }

    public static void a(nk arg0, Context arg1) {
        arg0.a(arg1);
    }

    private void b() {
        Iterator v0 = DaemonHelper.getIndicatorFiles(this.b).iterator();
        while(v0.hasNext()) {
            Object v1 = v0.next();
            if(v1 == null) {
                continue;
            }

            if(!new File(((String)v1)).delete()) {
                continue;
            }

            Timber.tag("maolei").d("delete indicatorFile success,file=" + (((String)v1)));
        }
    }

    public static void b(nk arg0) {
        arg0.a();
    }

    private void c() {
        IntentFilter v0 = new IntentFilter();
        v0.addAction("android.intent.action.SCREEN_ON");
        v0.addAction("android.intent.action.SCREEN_OFF");
        v0.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        v0.addAction("android.intent.action.USER_PRESENT");
        this.b.registerReceiver(new DaemonReceiver(), v0);
    }

    private boolean d() {
        boolean result = false;
//        if(RomUtils.isMiuiRom() && Build.VERSION.SDK_INT < 29) {
//            result = true;
//        }
        return result;
    }

    private void e() {
        if(this.d()) {
            new Thread(new nke()).start();
        }
    }

/*    public ok getCallback() {
        return this.c;
    }*/

/*    public mk getConfig() {
        return this.a;
    }*/

    public Context getContext() {
        return this.b;
    }

    public static nk getInstance() {
        return a;
    }

    public void init(Context arg3) {
        this.b = arg3;
        ProcessHolder.init(arg3);
        Timber.tag("maolei").d("SyncManager DaemonManager init, is main = " + ProcessHolder.IS_MAIN + " , is daemon =" + ProcessHolder.IS_DAEMON + " , is service =" + ProcessHolder.IS_SERVICE );
        DaemonHelper.init(arg3);
        if(ProcessHolder.IS_MAIN) {
            this.b();
        }

        if((ProcessHolder.IS_MAIN) || (ProcessHolder.IS_DAEMON)) {
            this.c();
            this.e();
            new Thread(new nka(this)).start();
        }

        DaemonHelper.startServices(arg3);
        if(((ProcessHolder.IS_MAIN) || (ProcessHolder.IS_SERVICE))) {
            if(ProcessHolder.IS_MAIN) {
                //pk.getInstance().addCallback(this.d);
            }
            else {
                //pk.setQueryInterval(1000);
            }

            //ScreenMonitorHelper.start();
        }

        //DaemonJobService.scheduleService(arg3);
        if(ProcessHolder.IS_MAIN) {
            new Handler(Looper.getMainLooper()).postDelayed(new nkb(this), 3000);
        }
    }
}


