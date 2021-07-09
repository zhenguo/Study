package com.qihoo.coordinator;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.myapplication.R;

public class ScrollUpActivity extends AppCompatActivity {
    private View mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_up);

        mScrollView = findViewById(R.id.scrollView);

        findViewById(R.id.action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mScrollView.getTranslationY() != 0) {
                    mScrollView.setTranslationY(0);
                } else {
                    mScrollView.setTranslationY(-1200);
                }
            }
        });
    }
}
