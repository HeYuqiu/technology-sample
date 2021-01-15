package com.hyq.springbootdemo;

import com.hyq.springbootdemo.service.TransactionTestService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

/**
 * @author Yuqiu.He
 * @date 2020-06-18
 */
@Component
public class CommandLineRunnerSample implements CommandLineRunner {

    @Autowired
    private TransactionTestService transactionTestService;

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void run(String... args) throws Exception {
        String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
//        transactionTestService.insertRecord();
    }
}
