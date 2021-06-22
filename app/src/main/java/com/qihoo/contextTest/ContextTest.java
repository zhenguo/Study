package com.qihoo.contextTest;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.View;

import com.quxiangtech.myapplication.R;
import com.qihoo.plugin.ProxyActivity;

public class ContextTest {
    public void startActivityUsingAppContext(Application application) {
        application.startActivity(new Intent(application, ProxyActivity.class));
    }

    public View inflateViewUsingAppContext(Application application) {
        View view = View.inflate(application, R.layout.activity_anr, null);
        return view;
    }

    public View inflateViewUsingActivityContext(Activity context) {
        View view = View.inflate(context, R.layout.activity_anr, null);
        return view;
    }
}
