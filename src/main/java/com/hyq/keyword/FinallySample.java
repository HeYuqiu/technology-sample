package com.hyq.keyword;

public class FinallySample {

    public void testFinally() {
        System.out.println(test());
    }

    public int test() {
        try {
            System.out.println("try");
            return 213;
        } finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        FinallySample sample = new FinallySample();
        sample.testFinally();
    }
}
