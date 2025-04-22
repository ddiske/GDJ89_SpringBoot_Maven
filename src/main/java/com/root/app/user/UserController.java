package com.root.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user/*")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("mypage")
	public String mypage(@ModelAttribute UserVO userVO) throws Exception {
		return "user/mypage";
	}
	
	@PostMapping("mypage")
	public String mypage(@Validated(UpdateGroup.class) UserVO userVO, BindingResult bindingResult, MultipartFile multipartFile) throws Exception {
		
		
		
		return "rediredt:./mypage";
	}
	
	@GetMapping("join")
	public String join(@ModelAttribute UserVO userVO) throws Exception {
		return "user/join";
	}
	
	@PostMapping("join")
	public String join(@Validated(JoinGroup.class) UserVO userVO, BindingResult bindingResult, MultipartFile multipartFile) throws Exception {
		
		if(userService.userErrorCheck(userVO, bindingResult)) {
			return "user/join";
		}
//		userService.join(userVO, multipartFile);
		
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
