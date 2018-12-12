package com.hyq.concurrent.keyword_volatile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 没加锁
 * Created by heyuqiu on 2018/12/11.
 * <p>
 * https://www.cnblogs.com/dolphin0520/p/3920373.html
 * <p>
 * 1、多个线程同时操作一个共享变量，会线程不安全(多试几次，有可能两个线程在同一个cpu中，是不会复现的)
 */
public class VolatileSample1 {
    static Integer tick = 0;
    //    static volatile Integer tick = 0;
    static CountDownLatch downLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100000; j++) {
                    tick = tick + 1;
                }
                downLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < 100000; k++) {
                    tick += 1;
                }
                downLatch.countDown();
            }
        }).start();

        downLatch.await();

        System.out.println(tick);
    }

}
