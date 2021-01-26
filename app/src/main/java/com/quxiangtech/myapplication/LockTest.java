package com.quxiangtech.myapplication;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    private int mCriticalResource = 1;
    private int mCriticalResource2 = 1;
    private ReentrantLock mReentrantLock = new ReentrantLock();
    private Semaphore mSemaphore = new Semaphore(2); // 允许多个线程访问某个资源，适合做限流，不能保证数据的同步，需要配合Lock、synchronized
    private Thread mThread1;
    private Thread mThread2;
    private Thread mThread3;
    private Thread mThread4;
    private AtomicInteger mAtomicInteger = new AtomicInteger(0);

    private void atomicTest() {
//        System.out.println("atomicTest start: " + mAtomicInteger.get());
        mAtomicInteger.incrementAndGet();
        System.out.println("atomicTest end: " + mAtomicInteger.get());
    }

    private void atomicTest1() {
//        System.out.println("atomicTest1 start: " + mAtomicInteger.get());
        mAtomicInteger.incrementAndGet();
//        System.out.println("atomicTest1 end: " + mAtomicInteger.get());
    }

    private void semaphoreTest2() {
        System.out.println("semaphoreTest2 start: " + mCriticalResource2);
        try {
            mSemaphore.acquire();
            mCriticalResource2++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mSemaphore.release();
        }

        System.out.println("semaphoreTest2 end: " + mCriticalResource2);
    }

    private void semaphoreTest3() {
//        System.out.println("semaphoreTest3 start: " + mCriticalResource2);
        try {
            mSemaphore.acquire();
            mCriticalResource2++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mSemaphore.release();
        }

        System.out.println("semaphoreTest3 end: " + mCriticalResource2);
    }

    private void semaphoreTest() {
        System.out.println("semaphoreTest start: " + mCriticalResource);
        try {
            mSemaphore.acquire();
            mCriticalResource++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mSemaphore.release();
        }

        System.out.println("semaphoreTest end: " + mCriticalResource);
    }

    private void semaphoreTest1() {
        System.out.println("semaphoreTest1 start: " + mCriticalResource);
        try {
            mSemaphore.acquire();
            mCriticalResource++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mSemaphore.release();
        }

        System.out.println("semaphoreTest1 end: " + mCriticalResource);
    }

    private synchronized void syncTest() {
        System.out.println("syncTest");
        mCriticalResource++;
        System.out.println("syncTest end: " + mCriticalResource);
    }

    private synchronized void syncTest1() {
        System.out.println("syncTest1");
        mCriticalResource++;
        System.out.println("syncTest1 end: " + mCriticalResource);
    }

    private void reentrantLockTest() {
        System.out.println("reentrantLockTest start: " + mCriticalResource);
        int holdCount = mReentrantLock.getHoldCount();
//        System.out.println("holdCount: " + holdCount);
        if (holdCount > 0) {
            System.out.println("currentThread " + Thread.currentThread().getId() + " hold count: " + holdCount);
            return;
        }
        mReentrantLock.lock();
        System.out.println("reentrantLockTest locked");
        try {
            mCriticalResource++;
        } finally {
            mReentrantLock.unlock();
        }
        System.out.println("reentrantLockTest end: " + mCriticalResource);
    }

    private void reentrantLockTest1() {
        System.out.println("reentrantLockTest1 start: " + mCriticalResource);
        int holdCount = mReentrantLock.getHoldCount();
//        System.out.println("holdCount: " + holdCount);
        if (holdCount > 0) {
            System.out.println("currentThread " + Thread.currentThread().getId() + " hold count: " + holdCount);
            return;
        }
        mReentrantLock.lock();
        System.out.println("reentrantLockTest1 locked");
        try {
            mCriticalResource++;
        } finally {
            mReentrantLock.unlock();
        }
        System.out.println("reentrantLockTest1 end: " + mCriticalResource);
    }

    public void runSyncTest() {
        mThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
//                    syncTest();
//                    reentrantLockTest();
                    semaphoreTest3();
//                    atomicTest();
                }
            }
        });
        mThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
//                    syncTest1();
//                    reentrantLockTest1();
                    semaphoreTest3();
//                    atomicTest1();
                }
            }
        });
        mThread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    semaphoreTest3();
//                    atomicTest();
                }
            }
        });
        mThread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    semaphoreTest3();
//                    atomicTest1();
                }
            }
        });

        mThread4.start();
        mThread2.start();
        mThread1.start();
        mThread3.start();
    }
}
