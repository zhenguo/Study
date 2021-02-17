package com.quxiangtech.designPattern.factoryMethod;

import com.quxiangtech.designPattern.simpleFactory.Fruit;

/**
 * 为每一个种类的水果建立一个水果
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        IFruitFactory factory;

        factory = new AppleFactory();
        Fruit fruit = factory.getFruit();
        fruit.price();
    }
}
