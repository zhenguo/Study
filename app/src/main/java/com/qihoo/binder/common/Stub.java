package com.qihoo.binder.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

public class Stub extends Binder implements IInterface {

    private static final String DESCRIPTION = "com.quxiangtech.binder.common.Stub";

    public Stub() {

    }

    @Override
    public IBinder asBinder() {
        queryLocalInterface(DESCRIPTION);
        return null;
    }
}
