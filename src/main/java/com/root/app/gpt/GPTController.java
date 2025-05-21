package com.root.app.gpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gpt/*")
public class GPTController {
	
	@Autowired
	private GPTEx gptEx;
	
	@GetMapping("input")
	public void input() {
		
	}
	
	@PostMapping("input")
	public void input(String prompt) {
		
	}

}
