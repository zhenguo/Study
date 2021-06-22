package com.qihoo.designPattern.abstractFactory;

import com.qihoo.designPattern.simpleFactory.Fruit;

public abstract class FruitFactory {
    public abstract Fruit getFruit();
    public abstract Bag getBag();
}
