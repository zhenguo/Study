package com.quxiangtech.designPattern.template;

public abstract class OrderMethod {
    public void doSomething() {
        System.out.println("做一些必要工作");
    }

    public void doSomething2() {
        System.out.println("做一些必要工作");
    }

    public void doSomething3() {
        System.out.println("做一些必要工作");
    }

    public abstract boolean pay();
}
