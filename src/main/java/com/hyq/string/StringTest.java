package com.hyq.string;

import com.hyq.concurrent.wait_join_notify.Join;

public class StringTest {
    public static void main(String[] args) {
        String x = "ab";
        String y = "a" + "b";
        System.out.println(x == y);// true

        String z1 = "a";
        String z2 = "b";
        String z3 = z1 + z2;
        System.out.println(x == z3);// false

        String z4 = x;
        System.out.println(x == z4);// true

        StringTest.Hyq h = new StringTest.Hyq();
        h.setName("jjj");
        StringTest.Hyq temp = h;
//        h = new Hyq();
        h.setName("bbb");
        change(h);
        System.out.println(temp.getName());  // bbb
    }

    public static void change(Hyq hyq) {
        hyq = new Hyq();
        hyq.setName("change");
    }

    static class Hyq {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
