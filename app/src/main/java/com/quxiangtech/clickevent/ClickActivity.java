package com.quxiangtech.clickevent;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.myapplication.R;

public class ClickActivity extends AppCompatActivity {
    private Button mClickBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_click);
        mClickBtn = findViewById(R.id.click_btn);
        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onClick()");
            }
        });
        mClickBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("onTouch() return false");
                return false;
            }
        });
    }
}
