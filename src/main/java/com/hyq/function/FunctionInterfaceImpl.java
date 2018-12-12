package com.hyq.function;

import java.util.function.Function;

/**
 * Created by heyuqiu on 2018/11/22.
 */
public class FunctionInterfaceImpl implements Function<Integer, String> {
    @Override
    public String apply(Integer integer) {
        return integer + "123";
    }

    @Override
    public <V> Function<V, String> compose(Function<? super V, ? extends Integer> before) {
        return null;
    }

    @Override
    public <V> Function<Integer, V> andThen(Function<? super String, ? extends V> after) {
        return null;
    }
}
