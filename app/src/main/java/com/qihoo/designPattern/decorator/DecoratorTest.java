package com.qihoo.designPattern.decorator;

import com.qihoo.designPattern.abstractFactory.AppFruitFactory;
import com.qihoo.designPattern.abstractFactory.Bag;
import com.qihoo.designPattern.abstractFactory.FruitFactory;
import com.qihoo.designPattern.simpleFactory.Fruit;

public class DecoratorTest {
    public static void main(String[] args) {
        FruitFactory fruitFactory = new AppFruitFactory();
        Fruit fruit = fruitFactory.getFruit();
        Bag bag = fruitFactory.getBag();
        BagDecorator bagDecorator = new CheckBagDecorator(bag); // 对包装进行装饰
        bagDecorator.pack(fruit);
    }
}
