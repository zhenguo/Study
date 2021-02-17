package com.quxiangtech.designPattern.abstractFactory;

import com.quxiangtech.designPattern.simpleFactory.Fruit;

public abstract class FruitFactory {
    public abstract Fruit getFruit();
    public abstract Bag getBag();
}
