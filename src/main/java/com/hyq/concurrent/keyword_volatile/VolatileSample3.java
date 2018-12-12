package com.hyq.concurrent.keyword_volatile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 加上锁（或者synchronized）+volatile(要不要都可以)
 * Created by heyuqiu on 2018/12/11.
 * volatile只是满足对内存的直接操作（不经过cpu高速缓存区），但是不能保证原子性，所以还是得加上锁
 * 那它有毛用？
 * 答：
 * Volatile从来就不是用来保证操作原子性的关键字，他只负责保证可见性和有序性，
 * 他的原子性是需要依靠锁来保证的。其实他也有一定的原子性，单个volatile变量的读操作和写操作是具有原子性的，
 * 但是一旦拥有多个操作，不再保证原子性。所以Volatile的使用需要你参照具体的场景来使用，并不是什么场景都能用，它是不能替代锁的作用的。
 * 之所以称之为轻量级锁，就是因为这个！
 *
 */
public class VolatileSample3 {
    public volatile int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileSample3 sample3 = new VolatileSample3();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        sample3.increase();
                    }
                }
            }).start();
        }

        while (Thread.activeCount() > 1) { //保证前面的线程都执行完
            System.out.println("activeCount   " + Thread.activeCount());
            Thread.yield();
        }

        System.out.println(sample3.inc);
    }
}


/**
 * Java线程中的Thread.yield( )方法，译为线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
 * <p>
 * 让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程。
 * <p>
 * yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保
 * <p>
 * 证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
 * <p>
 * 举个例子：一帮朋友在排队上公交车，轮到Yield的时候，他突然说：我不想先上去了，咱们大家来竞赛上公交车。然后所有人就一块冲向公交车，
 * <p>
 * 有可能是其他人先上车了，也有可能是Yield先上车了。
 * <p>
 * 但是线程是有优先级的，优先级越高的人，就一定能第一个上车吗？这是不一定的，优先级高的人仅仅只是第一个上车的概率大了一点而已，
 * <p>
 * 最终第一个上车的，也有可能是优先级最低的人。并且所谓的优先级执行，是在大量执行次数中才能体现出来的。
 */