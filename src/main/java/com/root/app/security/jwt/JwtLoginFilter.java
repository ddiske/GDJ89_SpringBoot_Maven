package com.root.app.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private JwtTokenManager jwtTokenManager;
	
	private AuthenticationManager authenticationManager;
	
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		this.setFilterProcessesUrl("/user/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		log.info("{}, {}", username, password);
		
		UsernamePasswordAuthenticationToken authenticationToken;
		authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
		
		log.info("auth : {}", authentication);
		
		return authentication;
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String accessToken = jwtTokenManager.createAccessToken(authResult);
		String refreshToken = jwtTokenManager.createRefreshToken(authResult);
		
		
		response.setHeader("AccessToken", accessToken);
		response.setHeader("RefreshToken", refreshToken);

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		response.setCharacterEncoding("UTF-8");
//		response.getWriter().print(failed.getMessage());
		
		// 예외 유형에 따른 값을 변경해서 전송 코드 작성
		int status = 500;
		if(failed instanceof BadCredentialsException) {
			status = 522;
		}else if(failed instanceof AccountExpiredException) {
			//사용자 유효기간이 만료
			status = 523;
		}else if(failed instanceof LockedException) {
			//계정 잠김
			status = 524;
		}else if(failed instanceof CredentialsExpiredException) {
			//비번 유효기간 종료
			status = 525;
		}else if(failed instanceof DisabledException) {
			//계정 사용 불가
			status = 526;
		}else {
			//없는 사용자
			status = 521;
		}
		response.setStatus(521);;
		
	}

}
