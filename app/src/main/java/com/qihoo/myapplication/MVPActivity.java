package com.qihoo.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.qihoo.myapplication.model.TestViewModel;
import com.qihoo.myapplication.presenter.GoodsPresenter;
import com.qihoo.myapplication.view.GoodsView;
import com.qihoo.BaseActivity;

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
