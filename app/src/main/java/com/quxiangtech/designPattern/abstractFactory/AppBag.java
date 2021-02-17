package com.quxiangtech.designPattern.abstractFactory;

import com.quxiangtech.designPattern.simpleFactory.Fruit;

public class AppBag implements Bag {
    @Override
    public void pack(Fruit fruit) {
        System.out.println("pack: " + fruit.getName());
    }
}
