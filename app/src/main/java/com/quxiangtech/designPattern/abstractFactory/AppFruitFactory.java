package com.quxiangtech.designPattern.abstractFactory;

import com.quxiangtech.designPattern.simpleFactory.Apple;
import com.quxiangtech.designPattern.simpleFactory.Fruit;

public class AppFruitFactory extends FruitFactory{
    @Override
    public Fruit getFruit() {
        return new Apple();
    }

    @Override
    public Bag getBag() {
        return new AppBag();
    }
}
