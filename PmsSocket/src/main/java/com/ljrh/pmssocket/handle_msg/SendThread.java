package com.ljrh.pmssocket.handle_msg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread{

    private final Socket socket;
    public SendThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                String content = scanner.nextLine();
                sendData(content,socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendData(String content,Socket socket) throws IOException {
        OutputStream os = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(content);
        bw.newLine();
        bw.flush();
    }


}
