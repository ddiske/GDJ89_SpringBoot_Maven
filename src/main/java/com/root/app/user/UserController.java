package com.root.app.user;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public String mypage() throws Exception {
			
		return "user/mypage";
	}
	
	@GetMapping("updatePassword")
	public String updatePassword(@ModelAttribute UserVO userVO) throws Exception {
		return "user/updatePassword";
	}
	
	@PostMapping("updatePassword")
	public String updatePassword(@Validated(UpdateGroup.class) UserVO userVO, BindingResult bindingResult) throws Exception {
		
		
		
		return "redirect:./mypage";
	}
	
	@GetMapping("update")
	public String update(@ModelAttribute UserVO userVO, HttpSession session) throws Exception {
		
//		Enumeration<String> e = session.getAttributeNames();
//		while(e.hasMoreElements()) {
//			System.out.println(e.nextElement());
//		}
//		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
//		System.out.println(obj.getClass());
//		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
//		Authentication authentication = contextImpl.getAuthentication();
//		log.info("log : {}", authentication);
		
		return "user/update";
	}
	
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) UserVO userVO, BindingResult bindingResult, MultipartFile multipartFile, HttpSession session) throws Exception {
		
		if(bindingResult.hasErrors()) {
			return "user/update";
		}
		
		userService.update(userVO);
		
		return "redirect:./mypage";
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
		userService.join(userVO, multipartFile);
		
		return "user/login";
	}
	
	@GetMapping("login")
	public String login(@AuthenticationPrincipal UserVO userVO) throws Exception {
		if(userVO == null) {
			return "user/login";			
		}
		return "redirect:/";
	}
	
//	@GetMapping("logout")
//	public String logout(HttpSession session) throws Exception {
//		session.invalidate();
//		return "redirect:/";
//	}

}
