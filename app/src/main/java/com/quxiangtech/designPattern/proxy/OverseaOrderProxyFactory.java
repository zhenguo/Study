package com.quxiangtech.designPattern.proxy;

public class OverseaOrderProxyFactory extends OrderService{
    @Override
    public void takeOrder() {
        System.out.println("take oversea order");
    }
}
