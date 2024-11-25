package com.ljrh.fxjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class FxMainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        URL url =  new URL("file:FxJava/src/main/resources/main_page.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("客户端发送请求");
        stage.setScene(scene);
        stage.show();
    }
}
