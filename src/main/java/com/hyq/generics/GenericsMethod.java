package com.hyq.generics;

public class GenericsMethod {

    public <T> T testMethod(T t) {
        if (t instanceof String) {
            System.out.println("t is string :" + t.getClass().getSimpleName());
        }
        // 通过泛型方法可以获得对象的信息
        if (t instanceof Temp) {
            System.out.println("t is Temp :" + t.getClass().getSimpleName());
        }

        return t;
    }

    public static void main(String[] args) {
        GenericsMethod test = new GenericsMethod();
        test.testMethod("hyq");

        Temp temp = new Temp();
        temp.setName("zhushu");
        test.testMethod(temp);
    }

    public static class Temp {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

