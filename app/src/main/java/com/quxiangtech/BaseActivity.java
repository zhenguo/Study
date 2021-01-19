package com.quxiangtech;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.quxiangtech.myapplication.presenter.BasePresenter;
import com.quxiangtech.myapplication.view.IBaseView;

public abstract class BaseActivity<T extends BasePresenter, V extends IBaseView> extends AppCompatActivity {
    protected T mPresenter;

    abstract @LayoutRes
    protected int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        getLifecycle().addObserver(mPresenter);
        mPresenter = createPresenter();
        mPresenter.onAttachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getLifecycle().removeObserver(mPresenter);
        mPresenter.detachView();
    }

    protected abstract T createPresenter();
}
