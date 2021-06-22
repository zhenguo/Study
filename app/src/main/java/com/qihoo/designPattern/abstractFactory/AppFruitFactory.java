package com.qihoo.designPattern.abstractFactory;

import com.qihoo.designPattern.simpleFactory.Fruit;
import com.qihoo.designPattern.simpleFactory.Apple;

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
