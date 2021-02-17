package com.quxiangtech.designPattern.decorator;

import com.quxiangtech.designPattern.abstractFactory.Bag;
import com.quxiangtech.designPattern.simpleFactory.Fruit;

public class CheckBagDecorator extends BagDecorator {
    public CheckBagDecorator(Bag bag) {
        super(bag);
    }

    @Override
    public void pack(Fruit fruit) {
        super.pack(fruit);
        check();
    }

    private void check() {
        System.out.println("进行防伪检查");
    }
}
