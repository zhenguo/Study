package com.quxiangtech.myapplication;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;
import android.view.Choreographer;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.quxiangtech.binder.BinderTest;
import com.quxiangtech.binder.ServiceManager;
import com.quxiangtech.binder.ServiceManagerService;
import com.quxiangtech.hotfix.HotFix;
import com.quxiangtech.plugin.HookUtil;
import com.quxiangtech.plugin.LoadUtil;
import com.quxiangtech.zrouter.ZRouter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class TestApplication extends Application {
    private static final String TAG = "TestApplication";
    private final Object mLock = new Object();
    private Activity mCurrentActivity;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        try {
            HotFix.getInstance().init(this, base.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/patch.dex");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
//        if (BuildConfig.DEBUG) {
//            File file = base.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//            if (file != null) {
//                String path = file.getAbsolutePath() + "/trace";
//                System.out.println("startMethodTracing: " + path);
//                Debug.startMethodTracingSampling(path, 8 * 1024 * 1024, 1_000);
//            }
//        }
    }

    class ActivityLifeCycleCallback implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public synchronized void onActivityResumed(@NonNull Activity activity) {
            Log.d(TAG, "onActivityResumed: " + activity.getClass().getSimpleName());
            synchronized (mLock) {
                mCurrentActivity = activity;
            }
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    }

    class FPSFrameCallback implements Choreographer.FrameCallback {
        private long mLastFrameTimeNanos;
        private final long mFrameIntervalNanos;

        public FPSFrameCallback(long lastFrameTimeNanos) {
            mLastFrameTimeNanos = lastFrameTimeNanos;
            mFrameIntervalNanos = 1000000000 / 60;
        }

        @Override
        public void doFrame(long frameTimeNanos) {
            if (mLastFrameTimeNanos == 0) {
                mLastFrameTimeNanos = frameTimeNanos; // Vsync信号到来的时间
            }

            final long jitterNanos = frameTimeNanos - mLastFrameTimeNanos;
            if (jitterNanos >= mFrameIntervalNanos) {
                final long skippedFrames = jitterNanos / mFrameIntervalNanos; // 错过了多少帧
                if (skippedFrames > 1) {
                    Log.d(TAG, "Skipped " + skippedFrames + " frames! The application may be doing too much work on its main thread.");

                    synchronized (mLock) {
                        if (mCurrentActivity != null) {
                            // 上报丢帧的Activity 名称
                            Log.d(TAG, "mCurrentActivity: " + mCurrentActivity.getClass().getSimpleName());
                        }
                    }
                }
            }

            mLastFrameTimeNanos = frameTimeNanos;

            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    private Resources mPluginResources;

//    @Override
//    public Resources getResources() {
//        System.out.println("getResources: " + mPluginResources);
//        if (mPluginResources == null) {
//            mPluginResources = LoadUtil.loadPluginResource(getApplicationContext());
//        }
//
//        return mPluginResources != null ? mPluginResources : super.getResources();
//    }

    @Override
    public void onCreate() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectCustomSlowCalls()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .detectResourceMismatches()
                .detectUnbufferedIo()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectActivityLeaks()
                .detectCleartextNetwork()
                .detectContentUriWithoutPermission()
                .detectCredentialProtectedWhileLocked()
                .detectFileUriExposure()
                .detectImplicitDirectBoot()
                .detectLeakedClosableObjects()
                .detectLeakedRegistrationObjects()
                .detectLeakedSqlLiteObjects()
                .detectFileUriExposure().build());
        super.onCreate();

        LoadUtil.loadPlugin(this);

        Log.d(TAG, "onCreate");
        registerActivityLifecycleCallbacks(new ActivityLifeCycleCallback());
        Choreographer.getInstance().postFrameCallback(new FPSFrameCallback(System.nanoTime()));
        ZRouter.getInstance().init(getApplicationContext());

        final ServiceManager[] mService = new ServiceManager[1];
        bindService(new Intent(this, ServiceManagerService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected");
//                mService[0] = (ServiceManager) service;
//                mService[0].addCapability("test", new BinderTest());
//                try {
//                    BinderTest binderTests = (BinderTest) mService[0].getCapability("test");
//                    binderTests.testBinder();
//                } catch (Exception e) {
//                    Log.e(TAG, "onServiceConnected: ", e);
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService[0] = null;
            }
        }, Service.BIND_AUTO_CREATE);
    }
}
