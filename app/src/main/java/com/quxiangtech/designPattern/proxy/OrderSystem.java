package com.quxiangtech.designPattern.proxy;

public class OrderSystem {
    public static void main(String[] args) {
        int type = 0;
        OrderService service = new LocalOrderFactory();
        if (type == 0) {
            service.takeOrder();
        } else {
            service = new OverseaOrderProxyFactory();
            service.takeOrder();
        }

    }
}
