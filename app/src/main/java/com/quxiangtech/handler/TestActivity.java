package com.quxiangtech.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class TestActivity extends Activity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Field field = mHandler.getClass().getDeclaredField("mCallback");
            field.setAccessible(true);
            field.set(mHandler, new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    return false;
                }
            });
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
