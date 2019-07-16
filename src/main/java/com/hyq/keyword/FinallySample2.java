package com.hyq.keyword;

public class FinallySample2 {

    public void testFinally(boolean throwEx) {
        System.out.println(test(throwEx));
    }

    private int test(boolean throwEx) {
        try {
            if (throwEx)
                throw new RuntimeException("test");
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    public static void main(String[] args) {
        FinallySample2 sample = new FinallySample2();
        sample.testFinally(false);
        sample.testFinally(true);
    }
}
