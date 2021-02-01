package com.quxiangtech.plugin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.quxiangtech.myapplication.BuildConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class HookUtil {
    private static final String TARGET_INTENT = "target_intent";

    public static void hookActivityThreadH() {
        try {
            Handler.Callback mHookCallback = new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    switch (msg.what) {
                        case 159:
                            // ActivityThread.EXECUTE_TRANSACTION
                            // msg.obj == ClientTransaction
                            // private List<ClientTransactionItem> mActivityCallbacks; ==> LaunchActivityItem ==> private Intent mIntent;

                            try {
                                Class<?> launchActivityItemClass = Class.forName("android.app.servertransaction.LaunchActivityItem");
                                Field mIntentField = launchActivityItemClass.getDeclaredField("mIntent");
                                mIntentField.setAccessible(true);
                                Object mIntentFieldObj;

                                // mActivityCallbacks
                                Class<?> clientTransactionClass = Class.forName("android.app.servertransaction.ClientTransaction");
                                Field mActivityCallbacksField = clientTransactionClass.getDeclaredField("mActivityCallbacks");
                                mActivityCallbacksField.setAccessible(true);
                                List<Object> mActivityCallbacksFieldObj = (List<Object>) mActivityCallbacksField.get(msg.obj);
                                for (int i = 0; i < mActivityCallbacksFieldObj.size(); i++) {
                                    System.out.println("item: " + mActivityCallbacksFieldObj.get(i));
                                    if (mActivityCallbacksFieldObj.get(i).getClass().getName().equals("android.app.servertransaction.LaunchActivityItem")) {
                                        mIntentFieldObj = mIntentField.get(mActivityCallbacksFieldObj.get(i));
                                        System.out.println("找到mIntent");
                                        Intent targetIntent = ((Intent) mIntentFieldObj).getParcelableExtra(TARGET_INTENT);
                                        System.out.println("target_intent: " + targetIntent.toString());
                                        if (targetIntent != null) {
                                            mIntentField.set(mActivityCallbacksFieldObj.get(i), targetIntent);
                                        }
                                    }
                                }

                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 100:
                            try {
                                Field intentField = msg.obj.getClass().getDeclaredField("intent");
                                intentField.setAccessible(true);
                                Intent proxyIntent = (Intent) intentField.get(msg.obj);
                                Intent targetIntent = proxyIntent.getParcelableExtra(TARGET_INTENT);
                                if (targetIntent != null) {
                                    intentField.set(msg.obj, targetIntent);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            break;
                    }


                    return false;
                }
            };
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            Field mHField = activityThreadClass.getDeclaredField("mH");
            mHField.setAccessible(true);
            sCurrentActivityThreadField.setAccessible(true);
            Object activityThreadObj = sCurrentActivityThreadField.get(null);
            Handler mHObj = (Handler) mHField.get(activityThreadObj);

            Class<?> handlerClass = Class.forName("android.os.Handler");
            Field mCallbackField = handlerClass.getDeclaredField("mCallback");
            mCallbackField.setAccessible(true);
            mCallbackField.set(mHObj, mHookCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hookAMS() {
        try {
            Class<?> IActivityTaskManagerClass = Class.forName("android.app.IActivityTaskManager"); // hook对象

            Class<?> activityTaskManagerClass = Class.forName("android.app.ActivityTaskManager");
            Field IActivityTaskManagerSingletonField = activityTaskManagerClass.getDeclaredField("IActivityTaskManagerSingleton");
            IActivityTaskManagerSingletonField.setAccessible(true);
            Object IActivityTaskManagerSingletonObj = IActivityTaskManagerSingletonField.get(null);

            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            Object mInstanceObj = mInstanceField.get(IActivityTaskManagerSingletonObj);

            // 创建动态代理对象
            Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class[]{IActivityTaskManagerClass}, new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            // 在此个性Intent参数
                            System.out.println("invoke: " + method.getName());

                            method.getName();
                            if (TextUtils.equals(method.getName(), "startActivity")) {
                                int index = -1;
                                for (int i = 0; i < args.length; i++) {
                                    if (args[i] instanceof Intent) {
                                        index = i;
                                        break;
                                    }
                                }
                                if (index != -1) {
                                    // 找到了Intent
                                    Intent intent = (Intent) args[index]; // 实际要启动的插件里的Activity
                                    System.out.println("pluginActivity: " + intent.toString());

                                    // 替换为ProxyActivity
                                    Intent pluginIntent = new Intent();
                                    pluginIntent.setClassName("com.quxiangtech.myapplication", "com.quxiangtech.plugin.ProxyActivity"); // ProxyActivity 已经注册在AndroidManifest.xml，跳过AMS检查
                                    pluginIntent.putExtra(TARGET_INTENT, intent); // 将插件Activity Intent保存起来，在AMS返回至App进程后，替换回来

                                    args[index] = pluginIntent; // 替换为ProxyActivity
                                    System.out.println("component: " + pluginIntent.getComponent().toShortString());
                                    System.out.println("proxyActivity: " + args[index]);
                                }
                            }
//                                int result = ActivityTaskManager.getService()
//                                        .startActivity(whoThread, who.getBasePackageName(), intent,
//                                                intent.resolveTypeIfNeeded(who.getContentResolver()),
//                                                token, target != null ? target.mEmbeddedID : null,
//                                                requestCode, 0, null, options);
                            System.out.println("mInstanceObj: " + mInstanceObj.getClass().getCanonicalName());
                            System.out.println("args: " + Arrays.toString(args));
                            return method.invoke(mInstanceObj, args);
                        }
                    });
            mInstanceField.set(IActivityTaskManagerSingletonObj, proxyInstance);
            System.out.println("hookAMS successful");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
