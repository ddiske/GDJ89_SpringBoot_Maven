package com.root.app.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

@Component
public class SendMessageTest {
	
	@Autowired
	private ChatHandler chatHandler;
	
//	@Scheduled(cron = "*/30 * * * * *"
	public void send() throws Exception {
		
		WebSocketMessage<?> message = new TextMessage("알림");
		
		chatHandler.handleMessage(null, message);
		
	}

}
