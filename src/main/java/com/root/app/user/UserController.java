package com.root.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("join")
	public String join() throws Exception {
		return "user/join";
	}
	
	@PostMapping("join")
	public String join(UserVO userVO, MultipartFile multipartFile) throws Exception {
		
		userService.join(userVO, multipartFile);
		
		return "user/login";
	}
	
	@GetMapping("login")
	public String login() throws Exception {
		return "user/login";
	}
	
	@PostMapping("login")
	public String login(UserVO userVO, HttpSession session) throws Exception {
		userVO = userService.login(userVO);
		if(userVO == null) {
			
		}else {
			session.setAttribute("user", userVO);			
		}
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}

}
