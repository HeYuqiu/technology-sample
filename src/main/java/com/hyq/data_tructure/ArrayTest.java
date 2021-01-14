package com.hyq.data_tructure;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 数组测试
 *
 * @author Yuqiu.He
 * @date 2020-06-19
 */
public class ArrayTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        TimeUnit.SECONDS.sleep(20);
        System.out.println("array1");
        int[] array1 = new int[10000];
        for (int i = 0; i < 10000; i++) {
            array1[i] = 100;
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println("array2");
        int[] array2 = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            array2[i] = 100;
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println("array3");
        int[] array3 = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            array3[i] = 100;
        }
        TimeUnit.SECONDS.sleep(10);
        System.in.read();
    }
}
