package com.quxiangtech.injectView;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class InjectViewUtil {
    public static void doInjectView(Activity activity) throws IllegalAccessException {
        Class<?> cls = activity.getClass();
        Field[] fields = cls.getFields(); // 获取Public Filed
        for (Field field :
                fields) {
            if (field.isAnnotationPresent(InjectView.class)) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                View view = activity.findViewById(injectView.idRes());
                field.setAccessible(true);
                field.set(activity, view);
            }
        }
    }
}
