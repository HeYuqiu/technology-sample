package com.hyq.concurrent.lock;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class DeadLockSample {
    public static void main(String[] args) {
        String a = "lock1";
        String b = "lock2";
        new Thread(()->{
            synchronized (a){
                try {
                    System.out.println(Thread.currentThread().getName()+" get a sync");
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (b) {
                        System.out.println(Thread.currentThread().getName()+" get b sync");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (b){
                try {
                    System.out.println(Thread.currentThread().getName()+" get b sync");
                    TimeUnit.SECONDS.sleep(1);
                    synchronized (a) {
                        System.out.println(Thread.currentThread().getName()+" get b sync");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
