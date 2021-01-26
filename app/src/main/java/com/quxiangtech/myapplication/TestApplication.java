package com.quxiangtech.myapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.quxiangtech.zrouter.ZRouter;

public class TestApplication extends Application {
    private static final String TAG = "TestApplication";
    private final Object mLock = new Object();
    private Activity mCurrentActivity;

    class ActivityLifeCycleCallback implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
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

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate");
        registerActivityLifecycleCallbacks(new ActivityLifeCycleCallback());
        Choreographer.getInstance().postFrameCallback(new FPSFrameCallback(System.nanoTime()));
        ZRouter.getInstance().init(getApplicationContext());
    }
}
