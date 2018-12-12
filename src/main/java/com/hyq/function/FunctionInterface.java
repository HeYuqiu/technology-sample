package com.hyq.function;

/**
 * Created by heyuqiu on 2018/11/22.
 */
public interface FunctionInterface<T,R> {
	R apply(T t);
}
