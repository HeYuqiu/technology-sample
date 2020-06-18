package com.hyq.generics;

/**
 * 边界/囿[yòu]量词 （Bounded Quantification）
 * 首先是对参数化多态的扩展，支持给泛型变量添加边界，即：<T extends X>等，这样可以表示更加精确的类型约束，而不仅仅是 forall；不仅如此，Java 还支持 F-bounded，即类型变量可以出现在自己的上边界中：<T extends F<T>>，这有什么用呢？举个熟悉的例子：
 *
 * 注意 Fmin 方法的 <T extends Comparable<T>>，如果 Comparable 不能包含 T，即像 min 方法那样，就会丢失接口 Comparable compareTo 方法的参数类型 (变成 Object)，你会收到一个编译器的警告，说你绕过了编译检查直接使用了 raw type（参数化类型擦除后的类型）；详情可参考 F-bounded quantification。
 * @author Yuqiu.He
 * @date 2020-05-14
 */
public class BoundedQuantification {
    public static void main(String[] args) {
        Comparable<String> a = min("cat", "dog");
        Comparable<Integer> b = min(new Integer(10), new Integer(3));
        String str = Fmin("cat", "dog");
        Integer i = Fmin(new Integer(10), new Integer(3));
    }
    public static <S extends Comparable> S min(S a, S b) {
        if (a.compareTo(b) <= 0)
            return a;
        else
            return b;
    }
    public static <T extends Comparable<T>> T Fmin(T a, T b) {
        if (a.compareTo(b) <= 0)
            return a;
        else
            return b;
    }
}
