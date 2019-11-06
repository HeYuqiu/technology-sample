package com.hyq.concurrent.ThreadPool;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@SuppressWarnings("all")
public class ExecutorsTest {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool(10);
        ExecutorService executers = new ThreadPoolExecutor(
                0, // 核心线程数
                100,  // 最大线程数
                10,   // 线程没有任务执行时最多保持多久时间会终止
                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(100)); // 10表示请求的队列缓存长度，超过了的线程不会被执行
                new SynchronousQueue<Runnable>());   // 这种队列不会保存提交的任务，而是直接新建一个线程来执行该任务
        for (int i = 0; i < 100; i++) {
            executers.execute(() -> {
                System.out.println(Instant.now().toString() + Thread.currentThread().getName() + " running...");
                Instant now = Instant.now();
                while (Instant.now().isBefore(now.plusSeconds(5))) {
                    int f = 100 + 100;
                }
                System.out.println(Instant.now().toString() + Thread.currentThread().getName() + " end...");
            });
        }
        System.out.println(Thread.currentThread().getName() + " running...");
    }

}
