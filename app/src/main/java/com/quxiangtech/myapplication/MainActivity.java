package com.quxiangtech.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.util.LruCache;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.quxiangtech.contextTest.ContextTest;
import com.quxiangtech.binder.LargeBitmapBinder;
import com.quxiangtech.hotfix.HotFixTest;
import com.quxiangtech.myapplication.lock.LockTest;
import com.quxiangtech.myapplication.reflection.ReflectClass;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private LockTest mLockTest = new LockTest();

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

;
//        if (BuildConfig.DEBUG) {
//            Debug.stopMethodTracing(); // test code
//        }
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();

//        if (BuildConfig.DEBUG) {
//            System.out.println("stopMethodTracing");
//            Debug.stopMethodTracing(); // test code
//        }

        System.out.println("onUserInteraction");
        ReferenceQueue<Activity> mReferenceQueue = new ReferenceQueue<>();
        WeakReference<Activity> mWeakReference = new WeakReference<>(this, mReferenceQueue);
        // 测试cpu Profiler
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        HotFixTest hotFixTest = new HotFixTest();
        hotFixTest.throwException();

        ContextTest contextTest = new ContextTest();
        contextTest.inflateViewUsingActivityContext(this);
        contextTest.inflateViewUsingAppContext(getApplication());
        contextTest.startActivityUsingAppContext(getApplication());
    }

    private void reflectionTest() {
        ReflectClass.reflectNewInstance();
        ReflectClass.reflectPrivateConstructor();
        ReflectClass.reflectPrivateField();
        ReflectClass.reflectPrivateMethod();

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

//        Log.d(TAG, "powerManager.isDeviceIdleMode(): " + powerManager.isDeviceIdleMode());

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(MainActivity.this, "准备关机", Toast.LENGTH_LONG).show();
//                ReflectClass.shutDown();
//            }
//        }, 10000);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        Looper.getMainLooper().setMessageLogging();
        Handler handler = new Handler();
        reflectionTest();

        AtomicReference<LockTest> reference = new AtomicReference<>();

//        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new LayoutInflater.Factory2() {
//            @Nullable
//            @Override
//            public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//                View view;
//                if (-1 == name.indexOf('.')) {
//                    view = onCreateView(context, parent, name, attrs);
//                } else {
//                    view = createView(context, name, null, attrs);
//                }
//                return view;
//            }
//
//            @Nullable
//            @Override
//            public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//                return null;
//            }
//        });

        mLockTest.runSyncTest();
//        int i  = 0;
//        do {
//            System.out.println("死循环会不会造成ANR");
//            Message message = new Message();
//            handler.sendMessage(message);
//        } while (i == 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        System.out.println("onRequestPermissionsResult: " + requestCode);
        if (requestCode == 100) {

        }

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_MyApplication); // 为了在用户体验层面加快app启动速度，恢复Theme
        super.onCreate(savedInstanceState);

        // hookAMS 影响Activity的LaunchMode
//        HookUtil.hookAMS();
//        HookUtil.hookActivityThreadH();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        reportFullyDrawn();


        try {
            PathClassLoader pathClassLoader = new PathClassLoader(getApplicationInfo().sourceDir, getClassLoader());
            Class<?> c1 = pathClassLoader.loadClass("android.app.Activity");
            Class<?> c2 = pathClassLoader.loadClass("com.Router");

            DexFile dexFile = new DexFile(getApplicationInfo().sourceDir);
            Class<?> c3 = dexFile.loadClass("android.app.Activity", pathClassLoader);
            Class<?> c4 = dexFile.loadClass("com.Router", pathClassLoader);

            DexFile dexFile2 = new DexFile(getApplicationInfo().sourceDir);
            Class<?> c5 = dexFile2.loadClass("com.Router", getClassLoader());

            Log.d(TAG, "c1 == c3: " + (c1 == c3));
            Log.d(TAG, "c2 == c4: " + (c2 == c4));
            Log.d(TAG, "c2 == c5: " + (c2 == c5));
            Log.d(TAG, "c4 == c5: " + (c4 == c3));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "message executed");
            }
        }, 0);

        LruCache<String, String> lruCache = new LruCache<String, String>(10) {
            @Override
            protected int sizeOf(String key, String value) {
                return 10;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, String oldValue, String newValue) {
                Log.d(TAG, "entryRemoved: " + key);
            }
        };
        for (int i = 0; i < 10; i++) {
            lruCache.put(String.valueOf(i + 1), String.valueOf(i * 100));
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "lruCache: " + lruCache.toString());

                Log.d(TAG, "get 1: " + lruCache.get("1"));
                Log.d(TAG, "after get1 lruCache: " + lruCache.toString());

                Log.d(TAG, "put 11:" + lruCache.put("11", "1100"));
                Log.d(TAG, "after put 11 lruCache: " + lruCache.toString());
            }
        }, 10000);
    }

}