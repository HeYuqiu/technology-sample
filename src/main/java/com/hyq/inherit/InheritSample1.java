package com.hyq.inherit;

public class InheritSample1 {
    public static class Father {
        static {
            System.out.println("father static method");
        }

        public Father() {
            System.out.println("father constructor");
        }
    }

    public static class Son extends Father {
        static {
            System.out.println("sun static method");
        }

        public Son() {
            System.out.println("Son constructor");
        }
    }

    public static void main(String[] args) {
        Father sun = new Son();
    }

}
