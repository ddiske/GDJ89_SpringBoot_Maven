package com.root.app.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestWebSocket {
	
	@Autowired
	private ChatHandler chatHandler;
	
	@Scheduled(cron = "*/10 * * * * *")
	public void test() throws Exception {
		
		
		
	}

}
