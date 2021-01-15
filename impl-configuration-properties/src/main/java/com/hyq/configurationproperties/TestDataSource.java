package com.hyq.configurationproperties;

import lombok.Data;
import org.springframework.stereotype.Component;

@Config(prefix = "config")
@Component
@Data
public class TestDataSource {

    private String username;

    private String password;

    private int maxActiveCount;
}