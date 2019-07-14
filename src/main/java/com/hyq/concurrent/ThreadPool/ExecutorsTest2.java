package com.hyq.concurrent.ThreadPool;

import java.time.Instant;
import java.util.concurrent.*;

public class ExecutorsTest2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName()+" start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" end");
        });
        executorService.shutdown();
        System.out.println("shutdown");
    }

}
