package com.quxiangtech;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.annotation.Route;
import com.quxiangtech.myapplication.R;

import java.util.Map;

@Route("/zroute/zrouteTest")
public class ZRouteTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zroute_test);
    }
}
