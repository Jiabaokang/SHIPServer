package com.ship.server;

import com.ship.server.service.MailService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class FxMainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

//        Button button = new Button("显示一个按钮");
//        button.setOnAction(event -> {
//            sendMail();
//        });
//
//        VBox root = new VBox();
//        root.getChildren().add(button);
//        root.setAlignment(Pos.BASELINE_CENTER);
//
//        Scene scene = new Scene(root,600,400);
//        stage.setScene(scene);
//        // 初始化窗口
//        stage.setTitle("JavaFX 窗口");
//        // 显示窗口
//        stage.show();

        URL url =  new URL("file:src/main/resources/main_page.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("客户端发送请求");
        stage.setScene(scene);
        stage.show();
    }

    private void sendMail(){
        MailService mailService = new MailService();
        mailService.sendSimpleMail("jiabaokangsy@126.com","这是第二份邮件","这是我用Java发送的第二份邮件");
    }
}
