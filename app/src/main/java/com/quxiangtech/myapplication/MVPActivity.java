package com.quxiangtech.myapplication;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.quxiangtech.BaseActivity;
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
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected GoodsPresenter createPresenter() {
        return new GoodsPresenter();
    }

    @Override
    public void showErrorMessage(String message) {

    }
}
