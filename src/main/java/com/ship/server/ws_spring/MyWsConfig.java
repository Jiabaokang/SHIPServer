package com.ship.server.ws_spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

/**
 * WebSocket 配置类，用于注册 WebSocket 端点并将其暴露给 Spring 容器
 */
@Configuration
@EnableWebSocket
public class MyWsConfig implements WebSocketConfigurer {

    /**
     * 注入自定义的 WebSocket 处理器
     */
    @Resource
    MyHandler myHandler;

    /**
     * 注入自定义的 WebSocket 拦截器
     */
    @Resource
    MyWsInterceptor myWsInterceptor;

    /**
     * 注册 WebSocket 处理器并配置拦截器和允许的源
     *
     * @param registry WebSocket 处理器注册器
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 注册处理器，指定处理器的路径为 /myWs1
        registry.addHandler(myHandler, "/myWs1")
                // 添加拦截器
                .addInterceptors(myWsInterceptor)
                // 设置允许的源为任意
                .setAllowedOrigins("*");
    }
}

