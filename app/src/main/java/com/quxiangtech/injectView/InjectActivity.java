package com.quxiangtech.injectView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.myapplication.R;

public class InjectActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
    @InjectView(idRes = R.id.inject_view_btn)
    TextView mInjectView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
        try {
            InjectViewUtil.doInjectView(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mInjectView.setText("测试View注入成功");
    }
}
