package com.qihoo.designPattern.abstractFactory;

import com.qihoo.designPattern.simpleFactory.Fruit;

public class AppBag implements Bag {
    @Override
    public void pack(Fruit fruit) {
        System.out.println("pack: " + fruit.getName());
    }
}
