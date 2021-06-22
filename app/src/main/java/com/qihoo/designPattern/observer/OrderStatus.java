package com.qihoo.designPattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class OrderStatus extends Observable {
    private final List<Observer> mObservers = new ArrayList<>();

    public String getName() {
        return "订单状态";
    }

    public void addObserver(Observer o) {
        mObservers.add(o);
    }

    public void removeObserver(Observer o) {
        mObservers.remove(o);
    }

    public void cleanObserver() {
        mObservers.clear();
    }

    public void notifyObserver() {
        for (Observer o : mObservers) {
            o.update(this, "已发货");
        }
    }
}
