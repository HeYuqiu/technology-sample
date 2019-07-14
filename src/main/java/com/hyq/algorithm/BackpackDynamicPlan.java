package com.hyq.algorithm;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 0-1背包问题：动态规划解法
 * Reference：https://www.jianshu.com/p/a66d5ce49df5
 */
public class BackpackDynamicPlan {
    static int n;           // 描述物品个数
    static int c;           // 描述背包容量
    static int[] value;     // 描述物品价值
    static int[] weight;    // 描述物品重量

    public static void main(String[] args) {
        // 初始赋值操作
        value = new int[]{1500, 3000, 2000, 1200, 1700, 2300, 3400, 3500, 2000, 4500, 1900, 1500};
        weight = new int[]{1, 4, 3, 1, 2, 3, 4, 5, 3, 6, 3, 1};
        c = 9;
        n = 12;

        // 构造最优解的网格:3行4列
        int[][] maxValue = new int[n][c];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                maxValue[i][j] = 0;
            }
        }   // end for

        // 填充网格
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= c; j++) {
                if (i == 0) {
                    maxValue[i][j - 1] = (weight[i] <= j ? value[i] : 0);
                } else {
                    int topValue = maxValue[i - 1][j - 1];  // 上一个网格的值
                    int thisValue = (weight[i] <= j ?       // 当前商品的价值 + 剩余空间的价值
                            (j - weight[i] > 0 ? value[i] + maxValue[i - 1][j - weight[i]] : value[i])
                            : topValue);

                    // 返回 topValue和thisValue中较大的一个
                    maxValue[i][j - 1] = (topValue > thisValue ? topValue : thisValue);
                }   // end if
            }   // end inner for
        }   // end outer for

        // 打印结果二维数组maxValue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                System.out.printf("%6d", maxValue[i][j]);
            }
            System.out.println();
        }
    }
}
