package com.root.app.websocket;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatDAOTest {
	
	@Autowired
	private ChatDAO chatDAO;

	@Test
	void test() throws Exception {
		
		List<MessageVO> list = new ArrayList<>();
		MessageVO messageVO = new MessageVO();
		messageVO.setRoomNum(1L);
		messageVO.setReceiver("sender");
		messageVO.setSender("receiver");
		messageVO.setBody("testChat");
		list.add(messageVO);
		messageVO = new MessageVO();
		messageVO.setRoomNum(1L);
		messageVO.setReceiver("receiver");
		messageVO.setSender("sender");
		messageVO.setBody("testChat2");
		list.add(messageVO);
		
		int result = chatDAO.addChats(list);
		
		
		assertEquals(2, result);
	}

}
