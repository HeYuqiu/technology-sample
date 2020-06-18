package com.hyq.generics;

/**
 * 最后以 Java 类型系统是 Unsound 的证明来结束本文。Soundness 在逻辑上是指推导系统的一致性，即：不能推导出错误的定理；对应到类型系统上，就是指类型系统的 type rules 不能推导出错误的结果，比如我不能将 Integer 的值赋值给 String 的变量，但是在 Java8 里你可以，在论文《Java and Scala’s Type Systems are Unsound》的例子中：
 *
 * main 方法中，我们将 0 赋值给了 String 类型的 zero，编译没问题，因为类型检查通过了，运行时会报错。这段代码的关键在于coerce 方法中的：bind.upcast(constrain, t) ，constrain 类型是 Constrain<U,? super T> 当传参给 upcast 时，发生了通配符的捕获转换， ? 被赋予了新的类型变量 Z，并且 T <: Z，且因为 Constrain 的声明，隐含 Z <: U，在做 upcast 方法的类型检查时，我们发现约束：
 * @author Yuqiu.He
 * @date 2020-05-14
 */
public class Unsound {
    static class Constrain<A, B extends A> {}
    static class Bind<A> {
        <B extends A> A upcast(Constrain<A,B> constrain, B b) {
            return b;
        }
    }
    static <T,U> U coerce(T t) {
        Constrain<U,? super T> constrain = null;
        Bind<U> bind = new Bind<U>();
//        return bind.upcast(constrain, t);
        return null;
    }
    public static void main(String[] args) {
        String zero = Unsound.<Integer,String>coerce(0);
    }
}
