package com.ship.server.test.fxclient.utils;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;

public class AlertDialog {

    public static void showAlert(String title){
        showAlert(title,null);
    }

    public static void showAlert(String title, String message) {
        Alert.AlertType alertType = Alert.AlertType.WARNING;
        Alert alert = new Alert(alertType);
        alert.setTitle("信息提示");
        alert.setHeaderText(title);
        if (message != null) {
            alert.setContentText(message);
        }
        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent dialogEvent) {
                System.out.println(dialogEvent);
            }
        });
        // 显示对话框
        alert.show();
    }
}
