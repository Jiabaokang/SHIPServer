package com.ship.server.test.ws_java;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * java注解实现 - 监听websocket地址/myWs
 */
@ServerEndpoint("/myWs")
@Component
@Slf4j
public class WsServerEndpont {

    static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);
        log.info("有新的Websocket连接 打开");
    }

    @OnMessage
    public String onMessage(String message) {
        log.info("收到消息: {}", message);
        return "已收到你的消息： " + message;
    }

    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(session.getId());
        log.info("websocket连接 关闭");
    }

    /**
     * 每隔两秒向客户端发送心跳
     * @throws IOException
     */
    @Scheduled(fixedRate = 2000)
    public void sendMessage() throws IOException {
        for (Session session : sessionMap.values()) {

            //session.getAsyncRemote().sendText("定时发送消息");
            session.getBasicRemote().sendText("心跳");
        }
    }


}
