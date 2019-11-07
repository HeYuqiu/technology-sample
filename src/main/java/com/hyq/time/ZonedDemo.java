package com.hyq.time;

import java.time.*;

/**
 * @author Yuqiu.He
 * @date 2019-11-06
 */
public class ZonedDemo {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        System.out.println(Instant.now());
        System.out.println(OffsetDateTime.now());
        System.out.println(ZonedDateTime.now());
    }
}
