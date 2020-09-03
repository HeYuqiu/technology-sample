package com.hyq.concurrent.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yuqiu.He
 * @date 2020-08-06
 */
public class AtomicSample {
    private static int num = 0;
    private static AtomicInteger index = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10000; j++) {
                    index.incrementAndGet();
                    num = num + 1;
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        System.out.println(index.get());
        System.out.println(num);

    }
}
