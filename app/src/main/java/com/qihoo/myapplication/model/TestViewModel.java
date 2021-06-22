package com.qihoo.myapplication.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class TestViewModel extends AndroidViewModel {
    private final LiveData<String> mData;

    public TestViewModel(@NonNull Application application) {
        super(application);

        mData = new MutableLiveData<>();
    }

    public void observer(LifecycleOwner owner, Observer<? super String> observer) {
        mData.observe(owner, observer);
    }
}
