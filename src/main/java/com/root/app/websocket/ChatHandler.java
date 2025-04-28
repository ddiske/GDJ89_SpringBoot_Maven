package com.root.app.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.root.app.user.UserVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ChatHandler implements WebSocketHandler {
	
	//socket으로 연결된 전체 유저
 	//BroadCast
 	private List<WebSocketSession> list = new ArrayList<>();
 	
 	private Map<String, WebSocketSession> users = new HashMap<>();
 	
 	private Map<Long, StringBuffer> messages = new HashMap<>();
 	
 	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
 		//Client가 WebSocket 연결시 실행
 		log.info("session : {}", session);
 		log.info("p: {}", session.getPrincipal());
 		list.add(session);
// 		UserVO userVO = (UserVO)session.getPrincipal();
 		users.put(session.getPrincipal().getName(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		//WebSocket으로 연결된 Client가 메세지를 송신 했을때
 		
		log.info("{}", message.getPayload());
		
 		MessageVO mes = new MessageVO();
 		ObjectMapper om = new ObjectMapper();
 		mes = om.readValue(message.getPayload().toString(), MessageVO.class);
 		mes.setSender(session.getPrincipal().getName());
 		log.info("{}", mes);
 		
 		if(!messages.containsKey(mes.getRoomNum())) {
 			StringBuffer sb = new StringBuffer();
 			sb.append(message.getPayloadLength());
 			messages.put(mes.getRoomNum(), sb);
 		}else {
 			messages.get(mes.getRoomNum()).append(message.getPayload());
 		}
 		
 		users.get(mes.getReceiver()).sendMessage(message);
 		users.get(mes.getSender()).sendMessage(message);
 		
 		list.forEach(s ->{
 			try {
 				s.sendMessage(message);
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		});
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		//WebSocket오류가 발생 했을 때
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		// Websocket 연결이 종료 되었을 때
 		list.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		//용량이 큰 데이터를 나눠서 받을 수 있는지 여부를 결정
 		// true이면서 톰켓이 지원을 하면 handleMessage를 여러번 호출
 		return false;
	}
	
	

}
