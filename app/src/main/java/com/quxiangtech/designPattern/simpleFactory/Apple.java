package com.quxiangtech.designPattern.simpleFactory;

public class Apple implements Fruit {
    @Override
    public String getName() {
        return "Apple";
    }

    @Override
    public int price() {
        System.out.println("Apple spent 100");
        return 100;
    }
}
