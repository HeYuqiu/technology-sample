package com.hyq.concurrent.keyword_volatile;

import java.util.concurrent.CountDownLatch;

/**
 * 加上锁
 * Created by heyuqiu on 2018/12/11.
 * <p>
 */
public class VolatileSample2 {
    static Object lock = "lock";
    static Integer tick = 0;
    //    static volatile Integer tick = 0;
    static CountDownLatch downLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100000; j++) {
                    synchronized (lock) {
                        tick = tick + 1;
                    }
                }
                downLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < 100000; k++) {
                    synchronized (lock) {
                        tick += 1;
                    }
                }
                downLatch.countDown();
            }
        }).start();

        downLatch.await();

        System.out.println(tick);
    }

}
