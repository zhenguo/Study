package com.qihoo.designPattern.observer;

public class ObserverTest {
    public static void main(String[] args) {
        OrderStatus orderStatus = new OrderStatus();
        CustomerObserver observer = new CustomerObserver();
        orderStatus.addObserver(observer);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        orderStatus.notifyObserver();
    }
}
