package com.hyq.concurrent.wait_join_notify;

@SuppressWarnings("all")
public class Wait_Notify {
    public static void main(String[] args) {
        Service service = new Service();
        Object o = new Object();

        new Thread(() -> {
            service.testMethod(o);
        }).start();

        new Thread(() -> {
            service.synNotifyMethod(o);
        }).start();
    }

    public static class Service {

        public void testMethod(Object lock) {
            try {
                System.out.println("testMethod 竞争锁");
                synchronized (lock) {
                    System.out.println("testMethod 获得锁");
                    Thread.sleep(1000);
                    System.out.println("testMethod 释放锁，处于挂起状态，等待被唤起");
                    lock.wait();  // 释放锁，并等待被唤起，只有被notify唤起才能继续执行，获得lock锁没用
                    System.out.println("testMethod 从wait状态获取到锁");
                    System.out.println("testMethod 执行完毕");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void synNotifyMethod(Object lock) {
            try {
                System.out.println("synNotifyMethod 竞争锁");
                synchronized (lock) {
                    System.out.println("synNotifyMethod 获得锁");
                    Thread.sleep(1000);
                    System.out.println("synNotifyMethod 唤起另一个处理wait中的线程");
                    lock.notify(); // 不是释放锁，只是唤起线程，要执行完成会释放锁
                    System.out.println("synNotifyMethod 执行完毕");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
