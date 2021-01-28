package com.quxiangtech.binder.common;

import android.app.Person;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

public class Proxy implements IPersonManager{
    @Override
    public void addPerson(Person person) throws RemoteException {

    }

    @Override
    public List<Person> getPersonList() throws RemoteException {
        return null;
    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
