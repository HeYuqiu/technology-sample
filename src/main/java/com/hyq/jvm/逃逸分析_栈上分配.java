package com.hyq.jvm;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * 逃逸分析优化-栈上分配
 * 方法类的局部变量没有发生逃逸，不用在堆上分配，堆上配置还要占用回收时间和堆空间，栈上分配的话，随着栈空间的回收，局部对象也被回收
 * <p>
 * 虚拟机参数设置如下，表示做了逃逸分析  消耗时间在10毫秒以下
 * -server  -Xmx10M  -Xms10M
 * -XX:+DoEscapeAnalysis  -XX:+PrintGC
 * <p>
 * 虚拟机参数设置如下，表示没有做逃逸分析   消耗时间在1000毫秒以上
 * -server -Xmx10m  -Xms10m
 * -XX: -DoEscapeAnalysis -XX:+PrintGC
 *
 * @author Yuqiu.He
 * @date 2021/1/13
 */
public class 逃逸分析_栈上分配 {
    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println("消耗时间为:" + (e - b));
        List<String> paramters = ManagementFactory.getRuntimeMXBean().getInputArguments();
        for (String p : paramters) {
            System.out.println(p);
        }
    }

    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }
}
