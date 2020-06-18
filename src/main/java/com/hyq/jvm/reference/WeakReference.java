package com.hyq.jvm.reference;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeakReference {
    public static void main(String[] args) {

        //测试引用传递
        Map<String, String> a = new HashMap<>();
        a.put("1", "a");
        List<Map> b = new ArrayList<Map>();
        b.add(a);
        a = null;
        Map aa = b.get(0);
        System.out.println(aa.get("1"));
        //结论传递的是引用的副本
        //测试值传递
        int xx = 1;
        add(xx);
        System.out.println(xx);


        Integer num = 10;
        Test test = new Test();
        test.setNum(num);
        num = num - 1;
        System.out.println(test.getNum());
    }

    public static void add(int temp) {
        temp++;
    }

    @Data
    public static class Test {
        private Integer num;
    }
}
