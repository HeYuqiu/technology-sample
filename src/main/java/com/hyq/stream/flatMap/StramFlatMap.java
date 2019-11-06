package com.hyq.stream.flatMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的扁平化处理
 * Created by heyuqiu on 2018/11/21.
 */
public class StramFlatMap {
    private void test() {
        List<String> list1 = Arrays.asList("hyq1", "zs1");
        List<String> list2 = Arrays.asList("hyq2", "zs2");
        List<String> list3 = Arrays.asList("hyq3", "zs3");

        List<List<String>> integration = Arrays.asList(list1, list2, list3);
        List<String> collect = list1.stream().map(s -> produce(s)).collect(Collectors.toList());
        List<String> collect12 = list1.stream().map(this::produce).collect(Collectors.toList());
        List<String> collect11 = list1.stream().map(s -> {
            return s + "555";
        }).collect(Collectors.toList());
        List<String> collect2 = integration.stream().flatMap(strings -> strings.stream()).collect(Collectors.toList());
        List<Stream<String>> collect1 = integration.stream().map(strings -> strings.stream()).collect(Collectors.toList());

        list1.stream().filter(region -> region.contains("hyq"))
                .peek(region -> region = region + "ttt") //保留原始类型
                .forEach(region -> region = region + "yyy");

        List<String[]> collect3 = list1.stream().map(s -> s.split("")).collect(Collectors.toList());
        List<String> collect33 = list1.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        List<String> collect4 = list1.stream().flatMap(s -> Arrays.stream(s.split(""))).distinct().collect(Collectors.toList());
        collect4.forEach(this::produceVoid);
    }


    public static void main(String[] args) {
        new StramFlatMap().test();
    }

    private String produce(String source) {
        return source + "444";
    }

    private void produceVoid(String source) {
        System.out.println(source);
    }
}
