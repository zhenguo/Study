package com.quxiangtech.keeplive;

import android.app.Instrumentation;
import android.os.Bundle;

import timber.log.Timber;

public class ViInstrumentation extends Instrumentation {
    public ViInstrumentation(){
        super();
    }

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        Timber.tag("maolei").d("ViInstrumentation OnCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.tag("maolei").d("ViInstrumentation OnDestory");
    }
}
