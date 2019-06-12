package com.hyq.generics;

import lombok.Data;

/**
 * 泛型的作用：参数化类型，通过泛型我们可以类型作为参数传递给一个类或者方法，
 * 同时还能减少类型的强制转换带来的ClassCastException风险
 *
 */
public class GenericsSample1 {

    @Data
    public static class WithOutGenerics {
        private Object value;

        public static void main(String[] args) {
            WithOutGenerics generics = new WithOutGenerics();
            generics.setValue(123);
            Integer integer = (Integer) generics.getValue();
            System.out.println(integer);
            generics.setValue("hyq");
            String string = (String) generics.getValue();
            System.out.println(string);
        }
    }

    @Data
    public static class WithGenerice<T> {
        private T value;

        public static void main(String[] args) {
            WithGenerice<String> generice = new WithGenerice();
            generice.setValue("hyq");
            String value = generice.getValue();
            System.out.println(value);
        }
    }

}
