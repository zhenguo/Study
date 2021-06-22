package com.qihoo.zplugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;

import java.lang.reflect.Method;

public class LoadResource {
    private static volatile LoadResource sInstance;
    private static volatile Resources sResource;

    public static LoadResource getInstance() {
        if (sInstance == null) {
            synchronized (LoadResource.class) {
                if (sInstance == null) {
                    sInstance = new LoadResource();
                }
            }
        }

        return sInstance;
    }

    public Resources getResource(Context context) {
        if (sResource == null) {
            sResource = loadPluginResource(context);
        }

        return sResource;
    }

    private Resources loadPluginResource(Context context) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/ZPlugin-debug.apk");
            System.out.println("loadPluginResource successful");
            return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
