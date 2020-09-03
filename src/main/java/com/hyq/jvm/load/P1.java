package com.hyq.jvm.load;

/**
 * jvm初始化时，会执行静态变量和静态代码块，是顺序执行的
 *
 * @author Yuqiu.He
 * @date 2020-09-03
 */
public class P1 {
    // 静态代码块中只能访问代码块之前的变量，定义在他之后的变量，只能赋值，不能访问，编译器检查不通过
    static {
        i = 0;
//        System.out.println(i);
    }

    static int i = 1; // 由于顺序执行，所以最终i=1;

    public static void main(String[] args) {
        System.out.println(P1.i);
    }
}
