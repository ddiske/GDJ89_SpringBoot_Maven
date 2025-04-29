package com.root.app.websocket;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.root.app.user.UserVO;

@Mapper
public interface ChatDAO {
	
	public List<UserVO> getList() throws Exception;
	public List<MessageVO> room(MessageVO messageVO) throws Exception;
	public int addChat(MessageVO messageVO) throws Exception;
	public int addChats(List<MessageVO> list) throws Exception;

}
