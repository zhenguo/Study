package com.quxiangtech.myapplication.singleton;

public class TestSingleton {
    volatile static TestSingleton INSTANCE;

    private TestSingleton() {
    }

    public static TestSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (TestSingleton.class) {
                if (INSTANCE == null) {
                    return new TestSingleton();
                    // malloc
                    // init mem
                    // assignment
                }
            }
        }

        return new TestSingleton();
    }

    public void test() {

    }
}
