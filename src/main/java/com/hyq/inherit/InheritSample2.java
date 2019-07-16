package com.hyq.inherit;

import java.util.ArrayList;
import java.util.List;

public class InheritSample2 {

    public static void main(String[] args) {
        List<F> f = new ArrayList<>(3);
        System.out.println("---new子类时，构造函数的执行顺序：先递归父类后子类----");
        f.add(new F());
        f.add(new S());
        f.add(new D());
        System.out.println("---成员变量的隐藏----");
        f.forEach((item) -> {
            System.out.println(item.name);
        });
        System.out.println("---成员方法覆盖----");
        f.forEach((item) -> {
            item.say();
        });
    }
}

class F {
    public F() {
        System.out.println("[F] " + this.name);
    }

    String name = "father";

    public void say() {
        System.out.println("[F] say() " + this.name);
    }
}

class S extends F {
    String name = "son";

    public S() {
        System.out.println("[S] " + this.name);
    }

    public void say() {
        System.out.println("[S] say() " + this.name);
    }
}

class D extends F {
    String name = "daughter";

    public D() {
        System.out.println("[D] " + this.name);
    }

    public void say() {
        System.out.println("[D] say() " + this.name);
    }
}