package com.hyq.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射获取父类属性
 */
public class GetFatherFieldsByReflect {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> son = Class.forName("com.hyq.reflection.Son");
        Field[] declaredFields = son.getDeclaredFields();
        Field[] fields = son.getFields();
        Method[] declaredMethods = son.getDeclaredMethods();
        Method[] methods = son.getMethods();

        Class<?> superclass = son.getSuperclass();
        Field[] declaredFields1 = superclass.getDeclaredFields();
    }
}

class Father {
    private String faPri;
    public String faPub;

    public void fath() {
    }
}

class Son extends Father {
    private String sonPri;
    public String sonPub;

    public void son() {
    }
}