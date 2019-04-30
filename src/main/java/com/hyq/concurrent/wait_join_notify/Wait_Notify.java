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
                synchronized (lock) {
                    System.out.println("begin wait() ThreadName="
                            + Thread.currentThread().getName());
                    lock.wait();
                    Thread.sleep(3000);
                    System.out.println("  end wait() ThreadName="
                            + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void synNotifyMethod(Object lock) {
            try {
                synchronized (lock) {
                    System.out.println("begin notify() ThreadName="
                            + Thread.currentThread().getName() + " time="
                            + System.currentTimeMillis());
                    lock.notify();
                    Thread.sleep(3000);
                    System.out.println("  end notify() ThreadName="
                            + Thread.currentThread().getName() + " time="
                            + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
