package com;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.quxiangtech.myapplication.MVPActivity;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private static Map<String, Class<? extends Activity>> routers = new HashMap<>();

    private Router() {
    }

    private static volatile Router mInstance;

    public static Router getInstance() {
        if (mInstance == null) {
            synchronized (Router.class) {
                if (mInstance == null) {
                    mInstance = new Router();
                }
            }
        }

        return mInstance;
    }


    public static void init(Application application) {
        MVPActivity mvpActivity = new MVPActivity();
        mvpActivity.loadInto(routers);
    }

    public void register(String path, Class<? extends Activity> aClass) {
        routers.put(path, aClass);
    }

    public void startActivity(Activity activity, String path) {
        Class<? extends Activity> aClass = routers.get(path);
        if (aClass != null) {
            Intent intent = new Intent(activity, aClass);
            activity.startActivity(intent);
        }
    }
}
