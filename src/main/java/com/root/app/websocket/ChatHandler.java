package com.root.app.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	
	@Autowired
	private ChatDAO chatDAO;
	
	// socket으로 연결된 전체 유저
 	// BroadCast
 	private List<WebSocketSession> list = new ArrayList<>();
 	
 	// socket으로 연결된 전체 유저
 	// key : username, value : WebSocketSession
 	private Map<String, WebSocketSession> users = new HashMap<>();
 	
 	// 대화방의 대화 내용을 임시 저장
 	private Map<Long, List<MessageVO>> messages = new HashMap<>();
 	
 	
 	
 	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
 		//Client가 WebSocket 연결시 실행
 		list.add(session);
// 		UserVO userVO = (UserVO)((Authentication)session.getPrincipal());
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
 		
 		// 1:1 통신 시 DB에서 ROOM정보를 조회
 		List<MessageVO> rooms = chatDAO.room(mes);
 		mes.setRoomNum(rooms.get(0).getRoomNum()); 		
 		
// 		if(!messages.containsKey(mes.getRoomNum())) {
// 			List<MessageVO> list = new ArrayList<>();
// 			list.add(mes);
// 			messages.put(mes.getRoomNum(), list);
// 		}else {
// 			messages.get(mes.getRoomNum()).add(mes);
// 		}
 		
 		try {
 			users.get(mes.getReceiver()).sendMessage(message);			
		} catch (Exception e) {
			e.printStackTrace();
		}
 		users.get(mes.getSender()).sendMessage(message);
 		
 		chatDAO.addChat(mes);
// 		if(messages.get(mes.getRoomNum()).size() > 2) {
// 			List<MessageVO> copy = messages.get(mes.getRoomNum());
// 			messages.put(mes.getRoomNum(), new ArrayList<>());
// 			chatDAO.addChats(copy);
// 		}
 		
 		
// 		list.forEach(s ->{
// 			try {
// 				s.sendMessage(message);
// 			} catch (IOException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}
// 		});
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
 		users.remove(session.getPrincipal().getName());
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		//용량이 큰 데이터를 나눠서 받을 수 있는지 여부를 결정
 		// true이면서 톰켓이 지원을 하면 handleMessage를 여러번 호출
 		return false;
	}
	
	

}
