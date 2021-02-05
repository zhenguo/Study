package com.quxiangtech.hotfix;

import android.app.Application;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

    public void init(Application app, String patch) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        // 通过path获取到Element[]
        /**
         * 我们自己写的类通过PathClassLoader加载，parent指向BaseClassLoader(防止串改系统类)
         */
        PathClassLoader classLoader = (PathClassLoader) app.getClassLoader();
        Field pathListField = classLoader.getClass().getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathListObj = pathListField.get(classLoader); // DexPathList对象
        Method makeDexElementsMethod = pathListObj.getClass().getMethod("makeDexElements", ArrayList.class, File.class);
        List<String> dexPath = new ArrayList<>();
        dexPath.add(patch);
        Object[] patchElementsObj = (Object[]) makeDexElementsMethod.invoke(pathListObj, dexPath, new File(app.getExternalCacheDir().getAbsolutePath()));
        Object[] dexElementsObj = (Object[]) pathListObj.getClass().getDeclaredField("dexElements").get(pathListObj);

        // patchElementsObj和dexElementsObj，并且补丁包在第0位，优先加载
        System.arraycopy(patchElementsObj, patchElementsObj.length, dexElementsObj, 0, dexElementsObj.length);
    }
}
