package com.root.app.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private JwtTokenManager jwtTokenManager;
	
	private AuthenticationManager authenticationManager;
	
	// login 요청을 처리
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		this.setFilterProcessesUrl("/users/login");
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.info("로그인 시도");
		log.info("username : {}", request.getParameter("username"));
		log.info("password : {}", request.getParameter("password"));
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password"));
		
		// UserDetails의 loadByUsername
		Authentication authentication = this.authenticationManager.authenticate(token);
		
		log.info("result : {}", authentication);
		
		return authentication;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		log.info("authResult : {}", authResult);
		
		// Token 생성하고 Client에 전송
		String token = jwtTokenManager.createToken(authResult);
		log.info(token);
		Cookie cookie = new Cookie("token", token);
		cookie.setMaxAge(120);
		cookie.setPath("/");
//		cookie.setSecure(true);// SSL 통신시에만 쿠키를 저장
		cookie.setHttpOnly(true);// 자바스크립트에서 임의로 쿠키를 변경할 수 없게 함
		response.addCookie(cookie);

		response.sendRedirect("/");
		
//		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/jwtLoginResult.jsp");
//		view.forward(request, response);

	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// 로그인 실패 시
		log.info("failed : {}", failed);
		response.sendRedirect("/user/login");
	}

}
