package com.quxiangtech.myapplication.presenter;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.quxiangtech.myapplication.view.IBaseView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<T extends IBaseView> implements LifecycleObserver {
    public WeakReference<T> mView;

    public void onAttachView(T view) {
        mView = new WeakReference<>(view);
    }

    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {

    }
}
