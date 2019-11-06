package com.hyq.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedStatic {
    public  static void testLock() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "get Lock");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "release Lock");
    }

    public static void main(String[] args) {
        SynchronizedStatic syn = new SynchronizedStatic();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    syn.testLock();
//                    SynchronizedStatic.testLock();
//                    new SynchronizedStatic().testLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
