package com.qihoo.zplugin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.quxiangtech.zplugin.R;

public class TestPluginActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("启动插件里面的Activity成功");
//        setContentView(R.layout.layout_test_plugin);

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_test_plugin, null);
        setContentView(view);
    }
}
