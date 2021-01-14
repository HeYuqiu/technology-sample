package com.hyq.concurrent.ThreadPool;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yuqiu.He
 * @date 2020-08-06
 */
public class ExecutorsShutdownTest {

    private static AtomicInteger index = new AtomicInteger(0);

//    static CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                for (int j = 0; j < 10000; j++) {
                    index.incrementAndGet();
                }
//                latch.countDown();
            });
        }


        System.out.println("准备shutdown：" + new Date().getTime());
        executorService.shutdown(); // 非阻塞
        System.out.println("完成shutdown：" + new Date().getTime());

        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) { // 阻塞
                System.out.println("完成awaitTermination：" + new Date().getTime());
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

//        executorService.shutdown();  // 不能直接用shutdown，不然可能任务并没有执行完
//        latch.await();
        System.out.println(index.get());
        Thread.sleep(6000);
        System.out.println(index.get());
    }
}
