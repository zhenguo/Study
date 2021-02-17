package com.quxiangtech.designPattern.observer;

import java.util.Observable;
import java.util.Observer;

public class CustomerObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {

        System.out.println("from " + ((OrderStatus) o).getName() + " " + arg);
    }
}
