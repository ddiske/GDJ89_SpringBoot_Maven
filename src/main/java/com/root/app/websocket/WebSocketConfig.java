package com.root.app.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
 	private ChatHandler chatHandler;
 	
 	@Override
 	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
 		// TODO Auto-generated method stub
 		System.out.println("========= CHat Handler ========");
 		registry.addHandler(chatHandler, "/ws/chat").setAllowedOrigins("*");
 	}

}
