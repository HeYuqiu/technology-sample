package com.hyq.time;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Yuqiu.He
 * @date 2019-11-06
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        //当前月份的第一天的日期,2017-03-01
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()));

        //下一个月的第一天的日期,2017-04-01
        System.out.println(now.with(TemporalAdjusters.firstDayOfNextMonth()));

        //当前月份的最后一天,2017-03-31 --再也不用计算是28，29，30还是31
        System.out.println(now.with(TemporalAdjusters.lastDayOfMonth()));
    }
}
