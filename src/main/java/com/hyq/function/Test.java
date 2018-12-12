package com.hyq.function;

import java.util.function.Function;

/**
 * Created by heyuqiu on 2018/11/22.
 */
public class Test {
    public static void main(String[] args) {
        Function<Integer, String> impl = new FunctionInterfaceImpl();
        String apply = impl.apply(12);
        System.out.println(apply);


        // 用匿名内部类实现接口
        Function<Integer, String> fun = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return integer + "";
            }
        };

        // 用lambda表达式实现接口
        FunctionInterface<Integer, String> funLambda = (x) -> x + "111";
        String apply1 = funLambda.apply(123);
        System.out.println(apply1);
    }
}
