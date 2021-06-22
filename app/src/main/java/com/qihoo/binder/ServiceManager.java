package com.qihoo.binder;

import android.os.Binder;

import java.util.HashMap;

public class ServiceManager extends Binder {
    private static final HashMap<String, Binder> mCapabilities = new HashMap<>();
    private volatile static ServiceManager sInstance;

    public static ServiceManager getsInstance() {
        if (sInstance == null) {
            synchronized (ServiceManager.class) {
                if (sInstance == null) {
                    sInstance = new ServiceManager();
                }
            }
        }

        sInstance.addCapability("test", new BinderTest());
        return sInstance;
    }

    public void addCapability(String key, Binder binder) {
        mCapabilities.put(key, binder);
    }

    public Binder getCapability(String key) throws Exception {
        Binder binder = mCapabilities.get(key);
        if (binder != null) {
            return binder;
        }
        throw new Exception("Can't find capability " + key);
    }
}
