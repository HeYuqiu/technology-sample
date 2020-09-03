package com.hyq.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Yuqiu.He
 * @date 2020-06-18
 */
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hyq";
    }
}
