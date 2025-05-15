package com.root.app.security.jwt;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.root.app.user.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	
	@Value("${jwt.accessToken.validTime}")
	private long accessTokenValidTime;
	
	@Value("${jwt.refreshToken.validTime}")
	private long refreshTokenValidTime;
	
	@Value("${jwt.issuer}")
	private String jwtIssuer;
	
	@Value("${jwt.secretKey}")
	private String jwtSecretKey;
	private SecretKey key;
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() {
		String s = Base64.getEncoder().encodeToString(jwtSecretKey.getBytes());
		this.key = Keys.hmacShaKeyFor(s.getBytes());
	}
	
	public String createAccessToken(Authentication authentication) {
		return this.createToken(authentication, accessTokenValidTime);
	}
	
	public String createRefreshToken(Authentication authentication) {
		return this.createToken(authentication, refreshTokenValidTime);
	}
	
	private String createToken(Authentication authentication, long validTime) {
		return Jwts.builder()
				   .setSubject(authentication.getName())
				   .claim("roles", authentication.getAuthorities())
				   .setIssuedAt(new Date(System.currentTimeMillis()))
				   .setExpiration(new Date(System.currentTimeMillis()+validTime))
				   .setIssuer(jwtIssuer)
				   .signWith(key)
				   .compact();
	}
	
	public Claims tokenValidation(String token) throws Exception {
		return Jwts.parser()
				   .setSigningKey(key)
				   .build()
				   .parseClaimsJws(token)
				   .getBody();
	}
	
	public Authentication getAuthentication(String username) {
		UserDetails details = userService.loadUserByUsername(username);
		Authentication authentication = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
		return authentication;
	}
	

}
