package com.root.app.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.root.app.user.UserVO;
import com.root.app.websocket.ChatDAO;
import com.root.app.websocket.MessageVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private ChatDAO chatDAO;
	
	@GetMapping("/")
	public String home(@AuthenticationPrincipal UserVO userVO, HttpSession session) throws Exception {
		
		if(userVO != null) {
			MessageVO messageVO = new MessageVO();
			messageVO.setReceiver(userVO.getUsername());
			List<MessageVO> list = chatDAO.getMemo(messageVO);
			session.setAttribute("memoList", list);			
		}
		
		return "home";
	}

}
