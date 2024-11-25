package com.ljrh.pmssocket.server;

import com.ljrh.pmssocket.dao.IPmsUserDao;
import com.ljrh.pmssocket.handle_msg.ReceThread;
import com.ljrh.pmssocket.handle_msg.SendThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class SocketServerComponent implements CommandLineRunner {

    @Autowired
    IPmsUserDao pmsUserDao;

    //@Value("${tcp_port.port}")
    private int port = 8085;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public void run(String... args) throws Exception {
        startSocketServer();
    }

    private void startSocketServer() {
        try {
            log.info("启动Socket服务端口：{}", port);
            ServerSocket  socketServer = new ServerSocket(port);
            log.info("Socket 服务已启动,监听端口:{}",port);
            while (true) {
                // 等待客户端连接
                Socket clientSocket = socketServer.accept();
                log.info("客户端已连接地址为==>{}" ,clientSocket.getInetAddress());
                // 使用线程池处理客户端连接
                executorService.execute(() -> handleClient(clientSocket));
            }
        } catch (Exception e) {
            log.error("启动Socket服务失败{}", e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        ReceThread receThread = new ReceThread(clientSocket,pmsUserDao);
//        receThread.start();
        executorService.submit(receThread);

        SendThread sendThread = new SendThread(clientSocket);
//        sendThread.start();
        executorService.submit(sendThread);
    }
}
