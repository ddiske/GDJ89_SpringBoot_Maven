package com.root.app.aoptest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransportTest {

	@Autowired
	private Transport transport;
	
	@Autowired
	private Card card;
	
	@Test
	void test() {
		String res = transport.getBus(1);
		String res2 = transport.getSubway("blue");
		transport.walk();
		String res3 = transport.bicycle(1000);
		System.out.println(res);
		System.out.println(res2);
		System.out.println(res3);
		System.out.println("종료");
	}

}
