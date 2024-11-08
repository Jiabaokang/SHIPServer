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
        //
        SpringApplication.run(MainApplication.class, args);
        //启动App
        Application.launch(FxMainApp.class, args);
    }


}