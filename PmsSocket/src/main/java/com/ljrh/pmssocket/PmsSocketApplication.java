package com.ljrh.pmssocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ljrh.pmssocket.dao")
public class PmsSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmsSocketApplication.class, args);
    }

}
