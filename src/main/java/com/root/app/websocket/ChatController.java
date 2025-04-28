package com.root.app.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/chat/*")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@GetMapping("list")
	public String chat(Model model) throws Exception {
		model.addAttribute("list", chatService.getList());
		return "chat/list";
	}
	
	@GetMapping("room")
	@ResponseBody
	public List<MessageVO> room(MessageVO messageVO) throws Exception {
		return chatService.room(messageVO);
	}

}
