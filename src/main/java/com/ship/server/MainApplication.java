package com.ship.server;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//开启一个定时任务
@EnableScheduling
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        //@SpringBootApplication是一个组合注解：
        //@Configuration：表明该类是一个配置类，可以包含Spring的Bean定义和配置信息。
        //@EnableAutoConfiguration：启用Spring Boot的自动配置功能，根据类路径中的依赖自动配置Spring和相关的库。
        //@ComponentScan：启用组件扫描，自动扫描当前包及其子包中的组件，并将它们注册为Spring Bean。
        SpringApplication.run(MainApplication.class, args);
        //启动App
        Application.launch(FxMainApp.class, args);
    }


}