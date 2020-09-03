package com.hyq.inheritance.p1;

/**
 * @author Yuqiu.He
 * @date 2020-09-03
 */
public class InterfaceStaticFieldTest {
    interface Interface0 {
        int A = 0;
    }

    interface Interface1 extends Interface0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
//        public static int A = 4;
    }


    public static void main(String[] args) {
        // 父类和接口定义了A，编译器不知道取哪个
//        System.out.println(Sub.A);
    }
}
