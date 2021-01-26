package com.quxiangtech.myapplication.lock;

/**
 * 证明 synchronized是可重入锁
 */
public class LockTest2 {
    public static void main(String[] args) {
        Child child = new Child();
        child.doSomething();
    }

    public static class Child extends Parent {
        public synchronized void doSomething() {
            System.out.println("child doSomething");
            doanotherThing();
            System.out.println("child doSomething end");
        }

        private synchronized void doanotherThing() {
            System.out.println("do anotherThing()");
            super.doSomething();
        }
    }

    public static class Parent {
        public synchronized void doSomething() {
            System.out.println("parent doSomething()");
        }
    }
}
