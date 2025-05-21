package com.root.app.GPT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.root.app.gpt.GPTEx;

class GPTExTest {
	
	@Autowired
	private GPTEx gptEx = new GPTEx();

	@Test
	void test() throws Exception {
		gptEx.ex2();
	}

}
