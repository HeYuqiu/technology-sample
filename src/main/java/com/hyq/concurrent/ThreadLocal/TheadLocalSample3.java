package com.hyq.concurrent.ThreadLocal;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("all")
public class TheadLocalSample3 {
    private ThreadLocal<Integer> tlInt = new ThreadLocal<Integer>();
    private ThreadLocal<String> tlStr = new ThreadLocal<String>();

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        TheadLocalSample3 ts = new TheadLocalSample3();
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
        tlInt.set(100);
        tlStr.set("hyq");
        for (int i = 0; i < 1000; i++) {
            tlInt.set(tlInt.get() + 1);
            tlStr.set(tlStr.get() + 1);
        }
        System.out.println(Thread.currentThread().getName() + " threadlocalInt: " + tlInt.get());
        System.out.println(Thread.currentThread().getName() + " threadlocalStr: " + tlStr.get());
    }

}
