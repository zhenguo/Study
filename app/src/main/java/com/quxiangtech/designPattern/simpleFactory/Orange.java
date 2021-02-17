package com.quxiangtech.designPattern.simpleFactory;

public class Orange implements Fruit{
    @Override
    public String getName() {
        return "Orange";
    }

    @Override
    public int price() {
        System.out.println("Orange spent 399");
        return 399;
    }
}
