package com.hyq.springbootdemo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Yuqiu.He
 * @date 2021/2/2
 */
@Component
public class TestBean {
    static{
        System.out.println("static bean");
    }

    public TestBean() {
        System.out.println("contra");
    }

}
