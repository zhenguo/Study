package com.quxiangtech.designPattern.decorator;

import com.quxiangtech.designPattern.abstractFactory.Bag;
import com.quxiangtech.designPattern.simpleFactory.Fruit;

public class BagDecorator implements Bag {
    private Bag bag;

    public BagDecorator(Bag bag) {
        this.bag = bag;
    }
    @Override
    public void pack(Fruit fruit) {
        this.bag.pack(fruit);
    }
}
