package com.qihoo.designPattern.decorator;

import com.qihoo.designPattern.abstractFactory.Bag;
import com.qihoo.designPattern.simpleFactory.Fruit;

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
