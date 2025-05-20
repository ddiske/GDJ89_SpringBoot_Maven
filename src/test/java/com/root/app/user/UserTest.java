package com.root.app.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class UserTest {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test() throws Exception {
		UserVO userVO = new UserVO();
		userVO.setUsername("user");
		
		String pw = "12345678";
		userVO.setPassword(passwordEncoder.encode(pw));
		
		int result = userDAO.join(userVO);
		
		assertEquals(1, result);
	}

}
