package com.hyq.delay_queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuqiu.He
 * @date 2020-06-03
 */

public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建延时队列
        DelayQueue<Message> queue = new DelayQueue<Message>();
        // 添加延时消息,m1 延时3s
        Message m1 = new Message(1, "world", 3000);
        // 添加延时消息,m2 延时10s
        Message m2 = new Message(2, "hello", 10000);
        //将延时消息放到延时队列中
        queue.offer(m2);
        queue.offer(m1);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(() -> {
            while (true) {
                try {
                    Message take = queue.take();
                    System.out.println("消费消息id：" + take.getId() + " 消息体：" + take.getBody());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exec.shutdown();
        queue.offer(new Message(3, "3", 100000));
        TimeUnit.MILLISECONDS.sleep(500);
        queue.offer(new Message(4, "4", 20000));
    }
}
