package com.quxiangtech.binder;

import android.graphics.Bitmap;
import android.os.Binder;

import androidx.annotation.NonNull;

public class LargeBitmapBinder extends Binder {
    private Bitmap mBitmap;

    public LargeBitmapBinder(@NonNull Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }
}
