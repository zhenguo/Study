package com.quxiangtech.keeplive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import timber.log.Timber;

public class DaemonReceiver extends BroadcastReceiver {
    public DaemonReceiver() {
        super();
    }

    public void onReceive(Context arg4, Intent arg5) {
        String v4 = arg5.getAction();
        Timber.tag("maolei").d("DaemonReceiver action=" + v4);
        if(v4 != null) {
            int v5_1 = -1;
            int v0 = v4.hashCode();
            if(v0 != -2128145023) {
                if(v0 != -1454123155) {
                    if(v0 != 823795052) {
                    }
                    else if(v4.equals("android.intent.action.USER_PRESENT")) {
                        v5_1 = 2;
                    }
                }
                else if(v4.equals("android.intent.action.SCREEN_ON")) {
                    v5_1 = 1;
                }
            }
            else if(v4.equals("android.intent.action.SCREEN_OFF")) {
                v5_1 = 0;
            }

            if(v5_1 != 0) {
                if(v5_1 != 1) {
                    return;
                }

                //ScreenMonitorHelper.resume();
                return;
            }

/*            if(RomUtil.isOppo()) {
                return;
            }*/

            //ScreenMonitorHelper.pause();
        }
    }
}

