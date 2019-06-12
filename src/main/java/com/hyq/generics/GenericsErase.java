package com.hyq.generics;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除:java的泛型信息只存在于代码编译阶段，在这之后和在进入JVM之前，所有与泛型相关的信息都被会擦除掉
 */
public class GenericsErase {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<String>();
        List<Integer> l2 = new ArrayList<Integer>();
        System.out.println(l1.getClass() == l2.getClass());
    }

    @Data
    public static class Erase<T> {
        private T value;
        private Integer integer;

        public Erase(T value) {
            this.value = value;
        }

        public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Erase<String> erase = new Erase("hyq");
            Class<? extends Erase> aClass = erase.getClass();
            System.out.println("Erase classname is :" + aClass.getName());
            // 我们可以通过反射获取到Erase类的所有信息：类名，有哪些方法，有哪些变量，变量的类型
            // 但是如果变量的类型是泛型呢？我们还能获取到么
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field field : declaredFields) {
                System.out.println("field name :" + field.getName() + ", type:" + field.getType().getName());
            }
            // 泛型的类型被擦除了，JVM不知道他是什么类型，只能认为他是object
            // 利用这一点，我们可以通过反射去set erase的value为int类型
            // erase.setValue(10); // 这样编译器会直接报错；
            Method setValue = aClass.getDeclaredMethod("setValue", Object.class);
            setValue.invoke(erase, 10);
        }
    }

}
