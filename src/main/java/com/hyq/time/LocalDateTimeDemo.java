package com.hyq.time;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * @author Yuqiu.He
 * @date 2019-11-06
 */
public class LocalDateTimeDemo {

    public static void main(String[] args) {
        LocalDateTime now =  LocalDateTime.now();
        switch (now.get(ChronoField.AMPM_OF_DAY)) {
            case 0:
                System.out.println("上午");
            case 1:
                System.out.println("下午");
        }


    }
}
