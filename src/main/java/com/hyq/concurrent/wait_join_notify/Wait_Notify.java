package com.hyq.concurrent.wait_join_notify;

@SuppressWarnings("all")
public class Wait_Notify {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+" running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" end...");
        });

        Thread waitThread = new Thread(() -> {
            try {
                while (true) {
                    thread.wait();
                    System.out.println(Thread.currentThread().getName()+" wait...");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread notifyThread = new Thread(() -> {
            try {
                while (true) {
                        thread.notify();
                    System.out.println(Thread.currentThread().getName()+" wait...");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
