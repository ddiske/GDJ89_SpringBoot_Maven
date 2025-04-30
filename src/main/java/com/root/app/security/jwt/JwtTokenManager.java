package com.root.app.security.jwt;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	
	@Value("${jwt.accessToken.validTime}")
	private Long accessTokenValidTime;
	
	@Value("${jwt.issuer}")
	private String issuer;
	
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	private SecretKey key;
	
	@PostConstruct // 생성자도 사용 가능
	public void init() {
		String s = Base64.getEncoder().encodeToString(secretKey.getBytes());
		key = Keys.hmacShaKeyFor(s.getBytes());
	}
	
//	Token 생성
	public String createToken(Authentication authentication) {
		return  Jwts.builder()
					.setSubject(authentication.getName())// 사용자의 username
					// 개발자가 원하는 추가 정보를 담을 수 있는
					.claim("roles", authentication.getAuthorities())
					.setIssuedAt(new Date(System.currentTimeMillis()))// Token 발급 시간
					.setExpiration(new Date(System.currentTimeMillis()+accessTokenValidTime))
					.setIssuer(issuer)// Token 발급자
					.signWith(key)
					.compact();
		
	}

}
