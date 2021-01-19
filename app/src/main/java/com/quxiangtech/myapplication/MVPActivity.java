package com.quxiangtech.myapplication;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.BaseActivity;
import com.quxiangtech.IRouteLoad;
import com.quxiangtech.myapplication.presenter.GoodsPresenter;
import com.quxiangtech.myapplication.view.GoodsView;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dalvik.system.DexFile;

public class MVPActivity extends BaseActivity<GoodsPresenter, GoodsView> implements GoodsView, IRouteLoad {

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

    @Override
    public void loadInto(Map<String, Class<? extends Activity>> routers) {
        routers.put("", MVPActivity.class);

        Set<String> classSet = new HashSet<>();
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), 0);
            DexFile dexFile = new DexFile(applicationInfo.sourceDir);
            Enumeration<String> enumeration = dexFile.entries();
            while (enumeration.hasMoreElements()) {
                classSet.add(enumeration.nextElement());
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
