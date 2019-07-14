package com.hyq.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapSample {
    public static void main(String[] args) {
        People people1= new People("hyq",23);
        People people2 = new People("zs",22);
        People people3 = new People("tt",23);
        People people4 = new People("77",7);
        ConcurrentHashMap<People,String> hashMap = new ConcurrentHashMap<>();
        hashMap.put(people1,"heyuqiu");
        hashMap.put(people2,"zhushu");
        hashMap.put(people3,"tiantian");
        hashMap.put(people4,"777");
        hashMap.get(people3);

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class People {
        private String name;
        private int age;

        @Override
        public int hashCode() {
            return age;
        }
    }
}
