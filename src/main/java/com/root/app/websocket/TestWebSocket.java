package com.root.app.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

@Component
public class TestWebSocket {
	
	@Autowired
	private ChatHandler chatHandler;
	
//	@Scheduled(cron = "*/10 * * * * *")
	public void test() throws Exception {
		
		WebSocketMessage<?> message = new TextMessage("공지 알려드립니다"); 
 		
 		chatHandler.handleMessage(null, message);
		
	}

}
