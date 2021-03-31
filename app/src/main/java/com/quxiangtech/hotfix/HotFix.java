package com.quxiangtech.hotfix;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dalvik.system.PathClassLoader;

public class HotFix {
    private static HotFix sInstance;

    public static HotFix getInstance() {
        if (sInstance == null) {
            synchronized (HotFix.class) {
                if (sInstance == null) {
                    sInstance = new HotFix();
                }
            }
        }

        return sInstance;
    }

    private HotFix() {
    }

    public void init(@NonNull Context app, @NonNull String patch) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        // 通过path获取到Element[]
        /**
         * 我们自己写的类通过PathClassLoader加载，parent指向BaseClassLoader(防止串改系统类)
         */
        if (!new File(patch).exists()) {
            System.out.println("patch file doesn't exists");
            return;
        }
        PathClassLoader classLoader = (PathClassLoader) app.getClassLoader(); // 获取当前App PathClassLoader
        Class<?> baseDexClassLoaderCls = Class.forName("dalvik.system.BaseDexClassLoader"); // 获取BaseDexClassLoader
        Field pathListField = baseDexClassLoaderCls.getDeclaredField("pathList"); // 从BaseDexClassLoader获取DexPathList的类型Field pathList
        pathListField.setAccessible(true);
        Object pathListObj = pathListField.get(classLoader); // 获取DexPathList对象
        if (pathListObj == null) {
            return;
        }
        // 调用makePathElements 将Patch.dex 加载为Elements[]
        Method makeDexElementsMethod = pathListObj.getClass().getDeclaredMethod("makePathElements", List.class, File.class, List.class);
        makeDexElementsMethod.setAccessible(true);
        ArrayList<File> dexPath = new ArrayList<>();
        dexPath.add(new File(patch));
        ArrayList<IOException> suppressedExceptions = new ArrayList<>();
        Object[] patchElementsObj = (Object[]) makeDexElementsMethod.invoke(pathListObj, dexPath, new File(app.getExternalCacheDir().getAbsolutePath()), suppressedExceptions);
        if (patchElementsObj == null) {
            return;
        }

        // 获取系统的dexElements
        Field dexElementsField = pathListObj.getClass().getDeclaredField("dexElements");
        dexElementsField.setAccessible(true);
        Object[] dexElementsObj = (Object[]) dexElementsField.get(pathListObj);
        if (dexElementsObj == null) {
            return;
        }

        // 创建新数组来组合Patch.dex和系统本来的Dex
        Object[] result = (Object[]) Array.newInstance(patchElementsObj.getClass().getComponentType(), patchElementsObj.length + dexElementsObj.length);

        // patchElementsObj和dexElementsObj，并且补丁包在第0位，优先加载
        System.arraycopy(patchElementsObj, 0, result, 0, patchElementsObj.length);
        System.arraycopy(dexElementsObj, 0, result, patchElementsObj.length, dexElementsObj.length);

        // 将新数组赋值给dexElements字段
        dexElementsField.set(pathListObj, result);
        System.out.println("HotFix init successful");
        Toast.makeText(app, "HotFix init successful", Toast.LENGTH_LONG).show();
    }
}
