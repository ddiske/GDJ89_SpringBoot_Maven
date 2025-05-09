package com.root.app.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(exception = Exception.class)
	public String ex1() {
		
		System.out.println("전역 에러");
		return "error/error";
	}

}
