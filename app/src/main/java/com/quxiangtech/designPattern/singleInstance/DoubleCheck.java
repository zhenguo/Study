package com.quxiangtech.designPattern.singleInstance;

public class DoubleCheck {
    /**
     * volatile禁止指令重排序，这样第一个判断
     */
    private static volatile DoubleCheck sInstance = new DoubleCheck();

    private DoubleCheck() {

    }

    public static DoubleCheck getInstance() {
        if (sInstance == null) { // 先检查是否为null,不为null 直接返回对象实例引用，提高效率
            synchronized (DoubleCheck.class) { // 由于synchronized开销较大，所以只在创建对象的时候使用，如果修饰方法，那么会带来不必要的访问开销
                if (sInstance == null) { // A和B同时进入第一个if语句，A先拿到锁，执行完创建过程后，B进来，如果没有这一句判断，那么还会重复创建对象
                    sInstance = new DoubleCheck();
                }
            }
        }
        return sInstance;
    }

    public void showMe() {
        System.out.println("I am a SingleInstance");
    }
}
