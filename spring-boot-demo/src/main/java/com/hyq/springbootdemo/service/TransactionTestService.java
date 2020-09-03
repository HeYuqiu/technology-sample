package com.hyq.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Yuqiu.He
 * @date 2020-06-18
 */
@Service
public class TransactionTestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertRecord() {
        jdbcTemplate.execute("insert into hello (name,age) values ('hyq',12)");
    }

}
