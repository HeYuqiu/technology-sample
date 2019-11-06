package com.hyq.concurrent.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class ExecutorsTest3 {
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newCachedThreadPool(10);
        ExecutorService executers = new ThreadPoolExecutor(
                2, // 核心线程数
                10,  // 最大线程数
                10,   // 线程没有任务执行时最多保持多久时间会终止
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(8)); // 10表示请求的队列缓存长度，超过了的线程不会被执行
        for (int i = 0; i < 30; i++) {
            System.out.println("第" + (i + 1) + "个线程");
            try {
                executers.execute(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + "开始");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception ex) {
                System.out.println("队列和线程池都满了！拒绝接受任务，休息1秒钟");
                Thread.sleep(10000);
                System.out.println("休息结束，继续...");
            }
        }
        System.out.println(Thread.currentThread().getName() + " 完毕...");
    }

}
