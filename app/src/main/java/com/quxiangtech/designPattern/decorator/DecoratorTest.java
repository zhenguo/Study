package com.quxiangtech.designPattern.decorator;

import com.quxiangtech.designPattern.abstractFactory.AppFruitFactory;
import com.quxiangtech.designPattern.abstractFactory.Bag;
import com.quxiangtech.designPattern.abstractFactory.FruitFactory;
import com.quxiangtech.designPattern.simpleFactory.Fruit;

public class DecoratorTest {
    public static void main(String[] args) {
        FruitFactory fruitFactory = new AppFruitFactory();
        Fruit fruit = fruitFactory.getFruit();
        Bag bag = fruitFactory.getBag();
        BagDecorator bagDecorator = new CheckBagDecorator(bag); // 对包装进行装饰
        bagDecorator.pack(fruit);
    }
}
