package com.quxiangtech.zplugin;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;

public class BaseActivity extends Activity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources resources = LoadResource.getInstance().getResource(getApplicationContext());
        mContext = new ContextThemeWrapper(getBaseContext(), 0);
        try {
            Field mResourcesFiled = mContext.getClass().getDeclaredField("mResources");
            mResourcesFiled.setAccessible(true);
            mResourcesFiled.set(mContext, resources);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public Resources getResources() {
//        Resources resources = LoadResource.getInstance().getResource(getApplicationContext());
//        System.out.println("插件中BaseActivity加载Resource: " + resources);
//        return resources == null ? super.getResources() : resources;
//    }
}
