package com.qihoo.designPattern.simpleFactory;

public class TestClass {
    public static void main(String[] args) {
        Fruit apple = FruitFactory.getFruit(FruitFactory.TYPE_APPLE);
        apple.price();
    }
}
