package com.quxiangtech.myapplication;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.PowerManager;
import android.os.Process;
import android.util.Log;
import android.util.LruCache;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.quxiangtech.myapplication.reflection.ReflectClass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private LockTest mLockTest = new LockTest();

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

        Handler handler = new Handler();
        reflectionTest();

        mLockTest.runSyncTest();
//        int i  = 0;
//        do {
//            System.out.println("死循环会不会造成ANR");
//            Message message = new Message();
//            handler.sendMessage(message);
//        } while (i == 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            Class<? > c1 = pathClassLoader.loadClass("android.app.Activity");
            Class<? > c2 = pathClassLoader.loadClass("com.Router");

            DexFile dexFile = new DexFile(getApplicationInfo().sourceDir);
            Class<? > c3 = dexFile.loadClass("android.app.Activity", pathClassLoader);
            Class<? > c4 = dexFile.loadClass("com.Router", pathClassLoader);

            DexFile dexFile2 = new DexFile(getApplicationInfo().sourceDir);
            Class<? > c5 = dexFile2.loadClass("com.Router", getClassLoader());

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