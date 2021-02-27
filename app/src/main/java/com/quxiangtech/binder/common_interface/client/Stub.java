package com.quxiangtech.binder.common_interface.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.quxiangtech.binder.common_interface.SayHi;

public class Stub extends Binder {
    private static final String DESCRIPTOR = "SayHi";
    public Stub() {
        attachInterface(new SayHi() {
            @Override
            public IBinder asBinder() {
                return this.asBinder();
            }

            @Override
            public String sayHi(String message) {
                return "Hello, Client";
            }
        }, DESCRIPTOR);
    }

    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case Stub.FIRST_CALL_TRANSACTION:
                data.enforceInterface(DESCRIPTOR);
                data.writeString("hello, server");
                break;
            default:break;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
