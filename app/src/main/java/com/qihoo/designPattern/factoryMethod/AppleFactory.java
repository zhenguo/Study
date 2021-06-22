package com.qihoo.designPattern.factoryMethod;

import com.qihoo.designPattern.simpleFactory.Apple;
import com.qihoo.designPattern.simpleFactory.Fruit;

public class AppleFactory implements IFruitFactory{
    @Override
    public Fruit getFruit() {
        return new Apple();
    }
}
