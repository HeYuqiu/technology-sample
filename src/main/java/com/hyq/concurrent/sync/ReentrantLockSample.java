package com.hyq.concurrent.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//  ReentrantLock可以等待终端，而synchronize不行
public class ReentrantLockSample {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Runnable runnable = () -> {
            try {
                if (lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                    System.out.println(Thread.currentThread().getName() + " 获得锁");
                    lock.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + " 2秒钟没有获取到锁，不等了");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        lock.lock();
        new Thread(runnable).start();
        Thread.sleep(3000);
        lock.unlock();
    }
}
