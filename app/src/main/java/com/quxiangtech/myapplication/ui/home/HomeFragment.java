package com.quxiangtech.myapplication.ui.home;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.quxiangtech.myapplication.R;
import com.quxiangtech.myapplication.RemoteService;
import com.quxiangtech.plugin.HookUtil;
import com.quxiangtech.plugin.LoadUtil;
import com.quxiangtech.plugin.ProxyActivity;
import com.quxiangtech.zrouter.ZRouter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
        return root;
    }
}