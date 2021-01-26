package com.quxiangtech.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.quxiangtech.BaseActivity;
import com.quxiangtech.myapplication.model.TestViewModel;
import com.quxiangtech.myapplication.presenter.GoodsPresenter;
import com.quxiangtech.myapplication.view.GoodsView;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dalvik.system.DexFile;

public class MVPActivity extends BaseActivity<GoodsPresenter, GoodsView> implements GoodsView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestViewModel testViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(TestViewModel.class);
        testViewModel.observer(this, s -> {

        });
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected GoodsPresenter createPresenter() {
        return null;
    }

    @Override
    public void showErrorMessage(String message) {

    }
}
