package com.quxiangtech.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.myapplication.R;

public class CActivity extends AppCompatActivity {
    private static final String TAG = "CActivity";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_c);
        findViewById(R.id.text_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.d(TAG, "onNewIntent: ");
    }

    public void launchA(View v) {
        startActivity(new Intent(this, AActivity.class));
    }

    public void launchB(View v) {
        startActivity(new Intent(this, BActivity.class));
    }

    public void launchC(View v) {
        startActivity(new Intent(this, CActivity.class));
    }

    public void launchD(View v) {
        startActivity(new Intent(this, DActivity.class));
    }
}
