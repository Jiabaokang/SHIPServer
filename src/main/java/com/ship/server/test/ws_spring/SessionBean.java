package com.ship.server.test.ws_spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

/**
 * 会话对象
 */
@AllArgsConstructor
@Data
public class SessionBean {

    private WebSocketSession session;
    private Integer clientId;
}
