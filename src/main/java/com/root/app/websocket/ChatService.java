package com.root.app.websocket;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.app.user.UserVO;

@Service
public class ChatService {
	
	@Autowired
	private ChatDAO chatDAO;
	
	public List<UserVO> getList() throws Exception {
		return chatDAO.getList();
	}
	
	public List<MessageVO> room(MessageVO messageVO) throws Exception {
		List<MessageVO> list = chatDAO.room(messageVO);
		
		if(list.size() == 0) {
			Calendar calendar = Calendar.getInstance();
 			long rm = calendar.getTimeInMillis();
 			messageVO.setRoomNum(rm);
			chatDAO.addChat(messageVO);
		}
		return chatDAO.room(messageVO);
	}

}
