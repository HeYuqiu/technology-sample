package com.hyq.concurrent.调优;

/**
 * 线上一台服务器 CPU 使用率100% 了，如果你碰到这样的情况，如何排查并找到问题原因？
 *
 * @author Yuqiu.He
 * @date 2021/1/17
 */
public class CPU占用过高实战 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(30 * 60 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.setName("thread-" + i);
            thread.start();
        }

        Thread highCpuThread = new Thread(() -> {
            int i = 0;
            while (true) {
                i++;
            }
        });
        highCpuThread.setName("HighCpu");
        highCpuThread.start();
    }
}
