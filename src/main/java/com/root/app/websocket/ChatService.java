package com.root.app.websocket;

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
		return chatDAO.room(messageVO);
	}

}
