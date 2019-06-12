package com.hyq.concurrent.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
 * <p>
 * 当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
 * <p>
 * main() 属于非守护线程。
 * <p>
 * 使用 setDaemon() 方法将一个线程设置为守护线程。
 */
public class DaemonThread {
    public static void main(String[] args) {
        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " daemon-thread");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemon.setDaemon(true);
        daemon.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " main-thread");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
