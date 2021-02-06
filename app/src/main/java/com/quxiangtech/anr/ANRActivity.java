package com.quxiangtech.anr;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.myapplication.R;

public class ANRActivity extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();

        ANRMonitor.getInstance().stopWatching();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);

        ANRMonitor.getInstance().startWatching();

        findViewById(R.id.input_anr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** /data/anr/anr_xxx文件
                 * "main" prio=5 tid=1 Sleeping
                 *   | group="main" sCount=1 dsCount=0 flags=1 obj=0x710c11f0 self=0xf7837800
                 *   | sysTid=14716 nice=-10 cgrp=default sched=0/0 handle=0xf7eabdc8
                 *   | state=S schedstat=( 1325562029 306161031 1830 ) utm=51 stm=81 core=0 HZ=100
                 *   | stack=0xff04b000-0xff04d000 stackSize=8192KB
                 *   | held mutexes=
                 *   at java.lang.Thread.sleep(Native method)
                 *   - sleeping on <0x0f1c4cdd> (a java.lang.Object)
                 *   at java.lang.Thread.sleep(Thread.java:440)
                 *   - locked <0x0f1c4cdd> (a java.lang.Object)
                 *   at java.lang.Thread.sleep(Thread.java:356)
                 *   at com.quxiangtech.anr.ANRActivity$1.onClick(ANRActivity.java:23)
                 *   at android.view.View.performClick(View.java:7125)
                 *   at com.google.android.material.button.MaterialButton.performClick(MaterialButton.java:992)
                 *   at android.view.View.performClickInternal(View.java:7102)
                 *   at android.view.View.access$3500(View.java:801)
                 *   at android.view.View$PerformClick.run(View.java:27336)
                 *   at android.os.Handler.handleCallback(Handler.java:883)
                 *   at android.os.Handler.dispatchMessage(Handler.java:100)
                 *   at android.os.Looper.loop(Looper.java:214)
                 *   at android.app.ActivityThread.main(ActivityThread.java:7356)
                 *   at java.lang.reflect.Method.invoke(Native method)
                 *   at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
                 *   at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
                 */
                /**
                 * 2021-02-06 20:34:22.106 2031-2134/system_process E/ActivityManager: ANR in com.quxiangtech.myapplication (com.quxiangtech.myapplication/com.quxiangtech.anr.ANRActivity)
                 *     PID: 14716
                 *     Reason: Input dispatching timed out (Waiting to send non-key event because the touched window has not finished processing certain input events that were delivered to it over 500.0ms ago.  Wait queue length: 2.  Wait queue head age: 7217.2ms.)
                 *     Parent: com.quxiangtech.myapplication/com.quxiangtech.anr.ANRActivity
                 *     Load: 0.15 / 0.12 / 0.05
                 *     CPU usage from 186273ms to 0ms ago (2021-02-06 12:31:14.066 to 2021-02-06 12:34:20.339):
                 *       4.4% 1792/surfaceflinger: 0.2% user + 4.2% kernel / faults: 171 minor 1 major
                 *       4.4% 2031/system_server: 0.5% user + 3.9% kernel / faults: 30623 minor 24 major
                 *       2.8% 2180/com.android.systemui: 0.1% user + 2.6% kernel / faults: 4334 minor
                 *       1.1% 1780/android.hardware.graphics.composer@2.1-service: 0% user + 1.1% kernel / faults: 27 minor
                 *       0.7% 2575/com.google.android.gms.persistent: 0% user + 0.6% kernel / faults: 5179 minor 2 major
                 *       0.4% 1783/android.hardware.sensors@1.0-service: 0% user + 0.4% kernel
                 *       0.4% 3078/com.google.android.gms: 0.1% user + 0.3% kernel / faults: 9730 minor
                 *       0.4% 2918/com.google.android.googlequicksearchbox:search: 0% user + 0.4% kernel / faults: 3717 minor
                 *       0.3% 1754/android.system.suspend@1.0-service: 0% user + 0.3% kernel
                 *       0% 1779/android.hardware.graphics.allocator@2.0-service: 0% user + 0% kernel / faults: 25 minor
                 *       0% 1789/audioserver: 0% user + 0% kernel / faults: 1 minor
                 *       0.2% 1796/adbd: 0% user + 0.2% kernel / faults: 2717 minor
                 *       0% 1756/android.hardware.audio@2.0-service: 0% user + 0% kernel / faults: 3 minor 4 major
                 *       0% 2414/com.google.android.apps.nexuslauncher: 0% user + 0% kernel / faults: 2280 minor 4 major
                 *       0.1% 1793/logcat: 0% user + 0.1% kernel / faults: 1 minor
                 *       0.1% 2255/com.android.phone: 0% user + 0% kernel / faults: 1451 minor
                 *       0% 1649/logd: 0% user + 0% kernel / faults: 33 minor
                 *       0% 1811/statsd: 0% user + 0% kernel
                 *       0% 3195/com.google.process.gapps: 0% user + 0% kernel / faults: 1530 minor
                 *       0% 1823/llkd: 0% user + 0% kernel
                 *       0% 1802/idmap2d: 0% user + 0% kernel / faults: 4952 minor
                 *       0% 1778/android.hardware.gnss@1.0-service: 0% user + 0% kernel
                 *       0% 1788/ashmemd: 0% user + 0% kernel
                 *       0% 1804/installd: 0% user + 0% kernel / faults: 1 minor
                 *       0% 13961/kworker/u8:0: 0% user + 0% kernel
                 *       0% 1749/netd: 0% user + 0% kernel / faults: 226 minor
                 *       0% 1750/zygote: 0% user + 0% kernel / faults: 827 minor
                 *       0% 2233/com.google.android.networkstack: 0% user + 0% kernel / faults: 133 minor
                 *       0% 2977/com.google.process.gservices: 0% user + 0% kernel / faults: 2023 minor
                 *       0% 8/rcu_preempt: 0% user + 0% kernel
                 *       0% 1669/jbd2/vdc-8: 0% user + 0% kernel
                 *       0% 1791/lmkd: 0% user + 0% kernel
                 *       0% 2473/com.google.android.inputmethod.latin: 0% user + 0% kernel / faults: 34 minor
                 *       0% 5715/com.google.android.gms.ui: 0% user + 0% kernel / faults: 177 minor
                 *       0% 7/ksoftirqd/0: 0% user + 0% kernel
                 *       0% 21/watchdog/2: 0% user + 0% kernel
                 *       0% 27/watchdog/3: 0% user + 0% kernel
                 *       0% 29/ksoftirqd/3: 0% user + 0% kernel
                 *       0% 841/kworker/0:1H: 0% user + 0% kernel
                 *       0% 1291/kworker/2:1H: 0% user + 0% kernel
                 *       0% 1295/kworker/3:1H: 0% user + 0% kernel
                 *       0% 1650/servicemanager: 0% user + 0% kernel
                 *       0% 1781/android.hardware.health@2.0-service.goldfish: 0% user + 0% kernel
                 *       0% 1813/wificond: 0% user + 0% kernel / faults: 2 minor
                 *       0% 1917/dhcpclient: 0% user + 0% kernel
                 *       0% 2309/wpa_supplicant: 0% user + 0% kernel
                 *       0% 3869/com.google.android.ims: 0% user + 0% kernel / faults: 125 minor 1 major
                 *       0% 9056/com.android.keychain: 0% user + 0% kernel / faults: 35 minor
                 *       0% 10908/com.google.android.partnersetup: 0% user + 0% kernel / faults: 18 minor
                 *       0% 11135/android.process.acore: 0% user + 0% kernel / faults: 25 minor
                 *      +0% 14481/kworker/0:2: 0% user + 0% kernel
                 *      +0% 14497/kworker/3:0: 0% user + 0% kernel
                 *      +0% 14498/kworker/u8:2: 0% user + 0% kernel
                 *      +0% 14642/kworker/2:2: 0% user + 0% kernel
                 *      +0% 14656/kworker/1:1: 0% user + 0% kernel
                 *      +0% 14716/com.quxiangtech.myapplication: 0% user + 0% kernel
                 *      +0% 14749/com.qux
                 */
                // 模拟block 10s
                System.out.println("start sleep 10s on MainThread");
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("MainThread waked");
            }
        });
    }
}
