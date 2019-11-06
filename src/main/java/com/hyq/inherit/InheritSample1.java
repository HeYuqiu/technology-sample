package com.hyq.inherit;

public class InheritSample1 {
    public static class Father {
        private String name;
        static {
            System.out.println("father static method");
        }

        public Father() {
            System.out.println("father constructor");
        }

        public Father(String name) {
            System.out.println("father constructor name: " + name);
            this.name = name;
        }
    }

    public static class Son extends Father {
        private String name;
        static {
            System.out.println("sun static method");
        }

        public Son() {
            System.out.println("Son constructor");
        }

        public Son(String name) {
            System.out.println("Son constructor name: " + name);
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Father sun = new Son("hyq");
    }

}
