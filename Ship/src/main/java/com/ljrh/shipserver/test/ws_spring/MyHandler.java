package com.ljrh.shipserver.test.ws_spring;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * websocket 处理器
 */
@Component
@Slf4j
public class MyHandler extends AbstractWebSocketHandler {

    private static Map<String, SessionBean> sessionBeanMapMap;
    private static AtomicInteger clientMaker;
    private static StringBuffer stringBuffer;
    static {
        sessionBeanMapMap = new ConcurrentHashMap<>();
        clientMaker = new AtomicInteger(0);
        stringBuffer = new StringBuffer();
    }

    /**
     * 连接建立后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        // 保存session
        SessionBean sessionBean = new SessionBean(session, clientMaker.getAndIncrement());
        sessionBeanMapMap.put(session.getId(), sessionBean);
        log.info(sessionBeanMapMap.get(session.getId()).getClientId()+":连接建立");
        stringBuffer.append(sessionBeanMapMap.get(session.getId()).getClientId()+":进入了群聊"+"<br/>");
        sendMessage(sessionBeanMapMap);
    }

    private void sendMessage(Map<String, SessionBean> sessionBeanMapMap) {
        for (String key : sessionBeanMapMap.keySet()) {
            try {
                sessionBeanMapMap.get(key).getSession().sendMessage(new TextMessage(stringBuffer.toString()));
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }
    }

    /**
     * 收到消息后
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        log.info(sessionBeanMapMap.get(session.getId()).getClientId()+":"+message.getPayload());
        stringBuffer.append(sessionBeanMapMap.get(session.getId()).getClientId()+":"+message.getPayload()+"<br/>");
        sendMessage(sessionBeanMapMap);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        if (session.isOpen()){
            session.close();
        }
        sessionBeanMapMap.remove(session.getId());
        log.info("websocket连接异常 已关闭");
    }

    /**
     * 连接关闭后
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        int clientId = sessionBeanMapMap.get(session.getId()).getClientId();
        //移除用户
        sessionBeanMapMap.remove(session.getId());
        log.info(clientId+":连接关闭");
        stringBuffer.append(clientId+":退出了群聊"+"<br/>");
        sendMessage(sessionBeanMapMap);
    }


    /**
     * 每隔两秒向客户端发送心跳
     * @throws IOException
     */
//    @Scheduled(fixedRate = 2000)
//    public void sendMessage() throws IOException {
//        for (SessionBean session : sessionBeanMapMap.values()) {
//            if (session.getSession().isOpen()){
//                session.getSession().sendMessage(new TextMessage("心跳"));
//            }
//
//        }
//    }



}
