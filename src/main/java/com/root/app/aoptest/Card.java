package com.root.app.aoptest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect // 어떤 advice를 어떤 point-cut에 언제 실행할 것인가를 설정
public class Card {
	
	@Before("execution( * com.root.app.aoptest.Transport.bicycle(int))")
	public void useMobile(JoinPoint joinPoint) {
		System.out.println("앱 결제");
		System.out.println(joinPoint.toString());
	}
	
	@Around("execution( * com.root.app.aoptest.Transport.get*(*))")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		
		System.out.println("탑승 전 카드 체크");
//		System.out.println(joinPoint.getKind());
//		System.out.println(joinPoint.getTarget());
		Object [] ar = joinPoint.getArgs();
		for(Object obj : ar) {
			System.out.println(obj.toString());
		}
		Object object = joinPoint.proceed();
		System.out.println(object.toString());
		System.out.println("하차 시 카드 체크");
		
		String result = "OK";
		
		return result;
	}

}
