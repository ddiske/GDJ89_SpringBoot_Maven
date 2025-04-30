package com.root.app.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	
	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		super(authenticationManager);
		this.jwtTokenManager = jwtTokenManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Token의 유무와 유효성 검증
		Cookie [] cookies = request.getCookies();
		String token = "";
		for(Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase("token")) {
				token = cookie.getValue();
			}
		}
		
		if(token.length() > 0) {
			try {
				Claims claims = jwtTokenManager.tokenValidation(token);
				Authentication authentication = jwtTokenManager.getAuthentication(claims.getSubject());
				// Spring Security Session에 등록
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		chain.doFilter(request, response);
	}

}
