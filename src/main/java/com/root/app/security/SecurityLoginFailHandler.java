package com.root.app.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String message = "";
		
		if(exception instanceof BadCredentialsException) {
			// 비밀번호 불일치
			message = "비밀번호가 일치하지 않습니다";
			message = "user.login.password";
		}else if(exception instanceof AccountExpiredException) {
			// 사용자 계정 유효기간 만료
			message = exception.getMessage();
		}else if(exception instanceof LockedException) {
			// 사용자 계정 잠김
			message = exception.getMessage();
		}else if(exception instanceof CredentialsExpiredException) {
			// 비밀번호 유효기간 만료
			message = exception.getMessage();
		}else if(exception instanceof DisabledException) {
			// 계정 사용 불가
			message = exception.getMessage();
		}else if(exception instanceof InternalAuthenticationServiceException) {
			// 존재하지 않는 사용자
			message = "아이디를 확인해주세요";
		}else {
			// 그 외
			message = "알 수 없는 오류";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		
		// forward
		// message 언어 변환이 안됨
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
//		request.setAttribute("code", message);
//		dispatcher.forward(request, response);
		
		// redirect
		response.sendRedirect("/user/login?message=".concat(message));
		
	}
	
	

}
