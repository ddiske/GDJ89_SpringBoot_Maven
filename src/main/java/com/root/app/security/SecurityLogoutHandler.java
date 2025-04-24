package com.root.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLogoutHandler implements LogoutHandler {

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		log.info("Before Session {} ", request.getSession()); 
		log.warn("Before Auth {} ", authentication);
		request.getSession().invalidate();
		 log.info("After Session {} ", request.getSession());
		 log.warn("After Auth {} ", authentication);
		 log.warn("New Session? {} ", request.getSession().isNew());
		
	}

}
