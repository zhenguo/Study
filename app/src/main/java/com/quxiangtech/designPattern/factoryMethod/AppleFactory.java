package com.quxiangtech.designPattern.factoryMethod;

import com.quxiangtech.designPattern.simpleFactory.Apple;
import com.quxiangtech.designPattern.simpleFactory.Fruit;

public class AppleFactory implements IFruitFactory{
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
