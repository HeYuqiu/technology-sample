package com.hyq.concurrent.实现Java线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自己实现一个简单的java线程池
 * 需要注意的点：
 * 1、使用worker线程来处理任务，worker线程阻塞的获取任务（死循环）
 * 2、核心线程就是worker线程，当核心线程满了时，就往队列里放，worker线程循环获取队列任务来执行
 * 3、不能每个任务都来创建线程，那就不是线程池了，也没法监控一个Thread对象是否结束，只有让Thread不停的死循环来保持它活着；
 * 4、先不实现MaxPoolSize逻辑；
 */
public class MyThreadPoolExecutor {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger();
        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(5);
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + count.incrementAndGet());
            });
        }
        Thread.sleep(500);
        executor.shutdown();
    }

    private volatile boolean runState = true;
    private int corePoolSize;
    private int nowPoolSize;
    private List<Worker> workers = new ArrayList<>();
    private static BlockingQueue<Runnable> queue;

    public MyThreadPoolExecutor(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        this.nowPoolSize = nowPoolSize;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void execute(Runnable runnable) throws InterruptedException {
        if (nowPoolSize < corePoolSize) {
            addWorkThread(runnable);
            nowPoolSize++;
        } else {
            queue.put(runnable);
        }
    }

    public void shutdown() {
        runState = false;
    }

    private void addWorkThread(Runnable runnable) {
        queue.offer(runnable);
        Worker worker = new Worker();
        Thread thread = new Thread(worker);
        thread.start();
        workers.add(worker);
    }

    // worker线程，阻塞循环
    private class Worker implements Runnable {
        @Override
        public void run() {
            while (true && runState) {
                Runnable poll = null;
                try {
                    poll = queue.take(); // 阻塞获取
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (poll != null) {
                    poll.run();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
