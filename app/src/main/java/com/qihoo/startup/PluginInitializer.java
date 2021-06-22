package com.qihoo.startup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import com.qihoo.plugin.LoadUtil;

import java.util.Collections;
import java.util.List;

public class PluginInitializer implements Initializer<LoadUtil> {
    @NonNull
    @Override
    public LoadUtil create(@NonNull Context context) {
        LoadUtil.loadPlugin(context);
        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.singletonList(HotFixInitializer.class);
    }
}
