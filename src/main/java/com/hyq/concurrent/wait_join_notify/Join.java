package com.hyq.concurrent.wait_join_notify;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * join是thread的方法，内部其实是通过循环调用Object.wait(0)的实现方式，就是等到当前线程，直到发送notify通知再继续往下走，一个线程在执行完毕后会发送notifyAll
 */
@SuppressWarnings("all")
public class Join {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " running...");

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end...");
        });

        thread.start();
        thread.join();

        System.out.println(Thread.currentThread().getName() + " end...");
    }
}
