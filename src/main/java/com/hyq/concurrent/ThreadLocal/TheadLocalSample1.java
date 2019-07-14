package com.hyq.concurrent.ThreadLocal;

@SuppressWarnings("all")
public class TheadLocalSample1 {
    private static ThreadLocal<Integer> tl = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };


    public static void main(String[] args) {
        new Thread(new AtomicIntThread()).start();
        new Thread(new AtomicIntThread()).start();
        new Thread(new AtomicIntThread()).start();
        new Thread(new AtomicIntThread()).start();
    }

    public static class AtomicIntThread implements Runnable{
        @Override
        public void run() {
            tl.set(100);
            for (int i = 0; i < 1000; i++) {
                tl.set(tl.get() + 1);
            }
            System.out.println(Thread.currentThread().getName() + ": " + tl.get());
        }
    }

}
