package com.hyq.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class RepeatUseStream {
    public static void main(String[] args) {
        // 流只能被消费一次
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        Stream<String> stream = strs.stream();
        boolean aa = stream.anyMatch(str -> str.equals("a"));
        boolean bb = stream.allMatch(str -> str.equals("a"));
        boolean cc = stream.noneMatch(str -> str.equals("a"));
        long count = stream.filter(str -> str.equals("a")).count();
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE
        System.out.println(count);// 4
    }
}
