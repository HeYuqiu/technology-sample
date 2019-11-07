package com.hyq.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Yuqiu.He
 * @date 2019-11-06
 */
public class FormatDemo {
    public static void main(String[] args) throws ParseException {
        withSimpleDateFormat();
        withDateTimeFormatter();
    }

    public static void withSimpleDateFormat() throws ParseException {
        //使用Date和SimpleDateFormat
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = simpleDateFormat.parse("2017-12-03 10:15:30");

        System.out.println(simpleDateFormat.format(date));
        //打印 2017-12-03 10:15:30
    }

    public static void withDateTimeFormatter() {
        //使用jdk1.8 LocalDateTime和DateTimeFormatter
        DateTimeFormatter pattern =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        //严格按照ISO yyyy-MM-dd验证，03写成3都不行
        LocalDateTime dt = LocalDateTime.parse("2017-12-03 11:15:30",pattern);

        System.out.println(dt);
        System.out.println(dt.format(pattern));
    }
}
