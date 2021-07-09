package com.qihoo.myapplication.ui.home;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.qihoo.anr.ANRActivity;
import com.qihoo.binder.localBinder.BindingActivity;
import com.qihoo.clickevent.ClickActivity;
import com.qihoo.coordinator.ScrollUpActivity;
import com.qihoo.customViews.TestDragActivity;
import com.qihoo.injectView.InjectActivity;
import com.qihoo.launchMode.AActivity;
import com.qihoo.zrouter.ZRouter;
import com.quxiangtech.myapplication.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HomeFragment extends Fragment {

    private Object mLock;

    void test() {
        synchronized (mLock) {
            // 面试问题： mLock没有初始化的话会报异常吗
            /**
             * java.lang.NullPointerException: Null reference used for synchronization (monitor-enter)
             */
            System.out.println("mLock 没有初始化");
        }
    }

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        test();
        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                return false;
            }
        });
        Message message = Message.obtain();
        handler.sendMessage(Message.obtain());
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Server");
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().startService(new Intent(v.getContext(), RemoteService.class));
            }
        });

        root.findViewById(R.id.local_binder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), BindingActivity.class));
            }
        });
        root.findViewById(R.id.anr_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ANRActivity.class);
                startActivity(intent);
            }
        });
        root.findViewById(R.id.zroute_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ZRouter.getInstance().startActivity(getActivity(), "/zroute/zrouteTest");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        root.findViewById(R.id.inject_view_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InjectActivity.class));
            }
        });
        root.findViewById(R.id.launch_mode_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AActivity.class));
            }
        });
        root.findViewById(R.id.reslove_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.quxiangtech.zplugin", "com.quxiangtech.zplugin.TestPluginActivity"));
                ResolveInfo resolveInfo = v.getContext().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (resolveInfo != null) {
                    System.out.println("resolveInfo: " + resolveInfo.toString());
                } else {
                    Toast.makeText(v.getContext(), "找不到未在AndroidManifest.xml中注册的Activity", Toast.LENGTH_LONG).show();
                }
            }
        });
        root.findViewById(R.id.plugin_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Class<?> pluginTestClass = Class.forName("com.quxiangtech.zplugin.PluginTest");
                    Method printf = pluginTestClass.getMethod("printf");
                    printf.invoke(null);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

//                Intent pluginIntent = new Intent();
//                pluginIntent.setClassName("com.quxiangtech.myapplication", "com.quxiangtech.plugin.ProxyActivity"); // ProxyActivity 已经注册在AndroidManifest.xml，跳过AMS检查
//                startActivity(pluginIntent);
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.quxiangtech.zplugin", "com.quxiangtech.zplugin.TestPluginActivity"));
//                intent.setClassName("com.quxiangtech.plugin", "TestPluginActivity");
                startActivity(intent);
            }
        });
        root.findViewById(R.id.event_comflict).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ClickActivity.class));
            }
        });

        root.findViewById(R.id.view_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), TestDragActivity.class));
            }
        });
        root.findViewById(R.id.text_scroll_up_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ScrollUpActivity.class));
            }
        });
        root.findViewById(R.id.ams_reflection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityManagerServiceOreo.Companion.openContentUri("");
            }
        });
        return root;
    }
}