package com.hyq.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author Yuqiu.He
 * @date 2019-11-06
 */
public class ToDateDemo {
    public static void main(String[] args) {
        Instant instant = new Date().toInstant();
        Date from = Date.from(instant);
        System.out.println(from);



    }
}
