package com.quxiangtech.designPattern.proxy;

public class LocalOrderFactory extends OrderService {
    @Override
    public void takeOrder() {
        System.out.println("take local order");
    }
}
