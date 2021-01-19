package com.quxiangtech.myapplication.reflection;

import android.content.Context;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectClass {
    private static final String TAG = "ReflectClass";

    public static void shutDown() {
        try {

            Class<?> cServiceManager = Class.forName("android.os.ServiceManager");
            Method mGetService = cServiceManager.getDeclaredMethod("getService", String.class);
            Object oPowerManagerService = mGetService.invoke(null, Context.POWER_SERVICE);
            Class<?> cIPowerManagerStub = Class.forName("android.os.IPowerManager$Stub");
            Method mShutdown = cIPowerManagerStub.getMethod("shutdown", boolean.class, String.class, boolean.class);
            Method mAsInterface = cIPowerManagerStub.getMethod("asInterface", IBinder.class);
            Object oIPowerManager = mAsInterface.invoke(null, oPowerManagerService);
            mShutdown.invoke(oIPowerManager, true, null, true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void reflectNewInstance() {
        try {
            Class<?> classBook = Class.forName("com.quxiangtech.myapplication.reflection.Book");
            Object objectBook = classBook.newInstance();
            Book book = (Book) objectBook;
            book.setName("Android reflectNewInstance");
            book.setAuthor("你大爷");
            Log.d(TAG, "reflect book = " + book.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void reflectPrivateConstructor() {
        try {
            Class<?> classBook = Class.forName("com.quxiangtech.myapplication.reflection.Book");
            Constructor<?> declaredConstructorBook = classBook.getDeclaredConstructor(String.class, String.class);
            declaredConstructorBook.setAccessible(true);
            Object objectBook = declaredConstructorBook.newInstance("反射私有构造", "你大爷");
            Book book = (Book) objectBook;
            Log.d(TAG, "反射私有构造函数 book = " + book.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void reflectPrivateField() {
        try {
            Class<?> classBook = Class.forName("com.quxiangtech.myapplication.reflection.Book");
            Object objectBook = classBook.newInstance();
            Field fieldTAG = classBook.getDeclaredField("TAG");
            fieldTAG.setAccessible(true);
            String tag = (String) fieldTAG.get(objectBook);
            Log.d(TAG, "reflectPrivateField tag = " + tag);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void reflectPrivateMethod() {
        Class<?> classBook = null;
        try {
            classBook = Class.forName("com.quxiangtech.myapplication.reflection.Book");
            Object objectBook = classBook.newInstance();
            Method methoddeclaredMethod = classBook.getDeclaredMethod("declaredMethod", int.class);
            methoddeclaredMethod.setAccessible(true);
            String result = (String) methoddeclaredMethod.invoke(objectBook, 1);
            Log.d(TAG, "reflectPrivateMethod string = " + result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static int main() {
        return 0;
    }
}
