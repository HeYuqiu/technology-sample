package com.hyq.concurrent.ThreadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("all")
public class TheadLocalSample2 {
    private ThreadLocal<Integer> tl = new ThreadLocal<Integer>();
    private int adInt;

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        TheadLocalSample2 ts = new TheadLocalSample2();
        es.submit(() -> {
            ts.atomicInt();
        });
        es.submit(() -> {
            ts.atomicInt();
        });
        es.submit(() -> {
            ts.atomicInt();
        });
        es.submit(() -> {
            ts.atomicInt();
        });

        es.shutdown();
    }

    public void atomicInt() {
        tl.set(100);
        adInt = 100;
        for (int i = 0; i < 1000; i++) {
            tl.set(tl.get() + 1);
            adInt++;
        }
        System.out.println(Thread.currentThread().getName() + " threadlocalInt: " + tl.get());
        System.out.println(Thread.currentThread().getName() + " adInt: " + adInt);
    }

}
