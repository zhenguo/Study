package com.qihoo.binder.common;

import android.app.Person;
import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

public interface IPersonManager extends IInterface {
    void addPerson(Person person) throws RemoteException;

    List<Person> getPersonList() throws RemoteException;
}
