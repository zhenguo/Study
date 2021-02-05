package com.quxiangtech.concurrent;

public class VolatileTest {

    static int condition = 0;
    public static void main(String[] args) {

        Runnable wait = new Runnable() {
            @Override
            public void run() {
                while (condition == 0) {
                    System.out.println("我在等待条件成立: " + Thread.currentThread().getId());
                }
                System.out.println("条件成立，执行业务逻辑" + Thread.currentThread().getId());
            }
        };

        Runnable notify = new Runnable() {
            @Override
            public void run() {
//                try {
//                    System.out.println("先休眠1秒" + Thread.currentThread().getId());
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("修改条件: " + Thread.currentThread().getId());
                condition = 1;
            }
        };

        new Thread(wait).start();
        new Thread(notify).start();
    }
}
