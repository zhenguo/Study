package com.quxiangtech;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.quxiangtech.myapplication.model.TestViewModel;
import com.quxiangtech.myapplication.presenter.BasePresenter;
import com.quxiangtech.myapplication.view.IBaseView;

public abstract class BaseActivity<T extends BasePresenter, V extends IBaseView> extends AppCompatActivity {
    protected T mPresenter;

    private ViewModelProvider mViewModelProvider;
    abstract @LayoutRes
    protected int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity();
        setContentView(getLayoutId());
        getLifecycle().addObserver(mPresenter);
        mPresenter = createPresenter();
        mPresenter.onAttachView((V) this);
        mViewModelProvider = new ViewModelProvider(this);
        TestViewModel testViewModel = mViewModelProvider.get(TestViewModel.class);
        testViewModel.observer(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        getLifecycle().removeObserver(mPresenter);
        mPresenter.detachView();
    }

    protected abstract T createPresenter();
}
