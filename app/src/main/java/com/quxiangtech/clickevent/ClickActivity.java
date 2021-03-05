package com.quxiangtech.clickevent;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.quxiangtech.myapplication.R;

public class ClickActivity extends AppCompatActivity {
    private static final String TAG = "ClickActivity";

    private ViewPager mViewPager;
    private Button mClickBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_click);
        mViewPager = findViewById(R.id.viewpager);
        mClickBtn = findViewById(R.id.click_btn);
        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick()");
            }
        });
        mClickBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch() return false");
                return true;
            }
        });
    }
}
