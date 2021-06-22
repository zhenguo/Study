package com.qihoo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qihoo.annotation.Route;
import com.quxiangtech.myapplication.R;

@Route("/zroute/zrouteTest")
public class ZRouteTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zroute_test);
    }
}
