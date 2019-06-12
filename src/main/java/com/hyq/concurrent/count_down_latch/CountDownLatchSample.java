package com.hyq.concurrent.count_down_latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 引用场景：等待其他线程执行完后再执行当前线程
 * 3）CountDownLatch：（同步计时器）三个运动员（子线程）等待裁判（主线程）发布命令，裁判发出命令后运动员开跑（子线程继续执行），然后裁判（主线程）等待3个运动员都跑完了，公布结果。
 * <p>
 * 必须是多线程的场景
 * Created by heyuqiu on 2018/11/29.
 */
public class CountDownLatchSample {
    private CountDownLatch latch = new CountDownLatch(1);

    public void method1() throws InterruptedException {
        System.out.println("method1 start...");
        latch.await();
        System.out.println("method1 run...");
    }

    public void method2() {
        System.out.println("method2 start...");
        latch.countDown();
        System.out.println("method2 run...");
    }

    public void triggerCountDown(CountDownLatchSample sample) {
        sample.method2();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchSample sample = new CountDownLatchSample();
        Runnable runnable = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                sample.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
        sample.method1();
//        sample.method2();
        System.out.println("main method done!");
    }
}
