package com.quxiangtech.designPattern.simpleFactory;

/**
 * 简单工厂模式 专业的人做专业的事 不要让别人瞎鸡巴摘水果
 */
public class FruitFactory {
    public final static int TYPE_APPLE = 1;
    public final static int TYPE_ORANGE = 2;
    public final static int TYPE_BANANA = 3;

    public static Fruit getFruit(int type) {
        Fruit result = null;
        switch (type) {
            case TYPE_APPLE:
                result = new Apple();
                break;
            case TYPE_ORANGE:
                result = new Orange();
                break;
            case TYPE_BANANA:
                result = new Banana();
                break;
        }

        return result;
    }
}
