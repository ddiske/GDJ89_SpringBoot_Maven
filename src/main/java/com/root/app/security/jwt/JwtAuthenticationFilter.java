package com.root.app.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
		
//		log.info("토큰 검증");
//		
		String token = request.getHeader("Authorization");
		if(token == null) {
			token = request.getParameter("t");
		}
//		log.info("{}", token.substring(token.indexOf(" ")+1));
		
		try {
			if(!token.substring(7).equalsIgnoreCase("null") && token.startsWith("Bearer ")) {
				Claims claims = jwtTokenManager.tokenValidation(token.substring(token.indexOf(" ")+1));
				log.info("{}", claims);
				Authentication authentication = jwtTokenManager.getAuthentication(claims.getSubject());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			// token 만료 시
			if(e instanceof ExpiredJwtException) {
				try {
					token = request.getHeader("RefreshToken");
					Claims claims = jwtTokenManager.tokenValidation(token);
					Authentication authentication = jwtTokenManager.getAuthentication(claims.getSubject());
					SecurityContextHolder.getContext().setAuthentication(authentication);
					token = jwtTokenManager.createAccessToken(authentication);
					response.setHeader("AccessToken", token);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
		chain.doFilter(request, response);
		
	}

	

}
