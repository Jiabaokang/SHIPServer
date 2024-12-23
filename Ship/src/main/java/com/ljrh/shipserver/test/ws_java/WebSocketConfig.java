package com.ljrh.shipserver.test.ws_java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocket 配置类，用于注册 WebSocket 端点并将其暴露给 Spring 容器
 */
@Configuration
public class WebSocketConfig {

    /**
     * 创建并注册一个 ServerEndpointExporter 实例，用于导出 WebSocket 端点
     * @return 返回 ServerEndpointExporter 实例
     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }


}
