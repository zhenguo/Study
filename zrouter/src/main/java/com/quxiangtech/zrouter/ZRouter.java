package com.quxiangtech.zrouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dalvik.system.DexFile;

public class ZRouter {
    private static final String TAG = "ZRouter";
    private static volatile ZRouter INSTANCE;
    public final Map<String, String> mRoutes = new HashMap<>();

    private ZRouter() {
    }

    public static ZRouter getInstance() {
        if (INSTANCE == null) {
            synchronized (ZRouter.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ZRouter();
                }
            }
        }

        return INSTANCE;
    }

    public static boolean routersRegistered = false;
    public void registerRouter() {

    }
    public void init(Context context) {
        if (!routersRegistered) {
            registerRouter();
        }
//        Set<String> classSet = new HashSet<>();
//        try {
//            String sourceDir = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).sourceDir;
//            DexFile dexFile = new DexFile(sourceDir);
//            while (dexFile.entries().hasMoreElements()) {
//                String entry = dexFile.entries().nextElement();
//                classSet.add(entry);
//                Log.d(TAG, "entry: " + entry);
//            }
//
//            try {
//                Class aClass = Class.forName("");
//                ((RouteRegister)aClass.newInstance()).register(mRoutes);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            }
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void register(String path, String activity) {
        mRoutes.put(path, activity);
    }

    public void startActivity(Activity src, String path) throws Exception {
        if (TextUtils.isEmpty(path)) {
            throw new Exception("path can not be null");
        }

        String target = mRoutes.get(path);

        if (target != null) {
            Class targetClass = Class.forName(target);
            Intent intent = new Intent(src, targetClass);
            src.startActivity(intent);
        } else {
            Log.d(TAG, "can't find target activity");
        }
    }
}
