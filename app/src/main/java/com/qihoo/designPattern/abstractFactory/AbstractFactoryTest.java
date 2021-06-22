package com.qihoo.designPattern.abstractFactory;

import com.qihoo.designPattern.simpleFactory.Fruit;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        FruitFactory fruitFactory = new AppFruitFactory();
        Fruit apple = fruitFactory.getFruit();
        Bag appleBag = fruitFactory.getBag();
        appleBag.pack(apple);
    }
}
