package com.hyq.time;

import java.time.Instant;

/**
 * 时间点
 * @author Yuqiu.He
 * @date 2019-11-07
 */
public class InstantDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000000);
        System.out.println(Instant.EPOCH);
        System.out.println(Instant.MAX);
        System.out.println(Instant.MIN);
    }
}
