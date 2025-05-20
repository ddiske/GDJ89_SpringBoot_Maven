package com.root.app.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.root.app.user.UserDAO;
import com.root.app.user.UserVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.info("Login Success : {}", authentication);
		
		UserVO userVO = (UserVO)authentication.getPrincipal();
		userVO.setStatus(true);
		
		try {
			int result = userDAO.statusChange(userVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		LoginUsers.NAMES.add(userVO.getUsername());
		
		// forward
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
//		dispatcher.forward(request, response);
		
		// redirect
		response.sendRedirect("/");
		
		
	}
	
	

}
