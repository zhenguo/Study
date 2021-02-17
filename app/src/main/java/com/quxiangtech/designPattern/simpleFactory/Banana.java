package com.quxiangtech.designPattern.simpleFactory;

public class Banana implements Fruit {
    @Override
    public String getName() {
        return "Banana";
    }

    @Override
    public int price() {
        System.out.println("Banana spent 200");
        return 200;
    }
}
