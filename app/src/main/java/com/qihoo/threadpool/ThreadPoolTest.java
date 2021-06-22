package com.qihoo.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    ExecutorService executorService;

    public ThreadPoolTest() {
        Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
