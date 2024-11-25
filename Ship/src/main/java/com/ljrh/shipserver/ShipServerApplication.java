package com.ljrh.shipserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication


//开启一个定时任务
@EnableScheduling
@SpringBootApplication
@MapperScan("com.ljrh.shipserver.dao")
public class ShipServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipServerApplication.class, args);
    }

}
