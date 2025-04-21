package com.root.app.aoptest;

import org.springframework.stereotype.Component;

@Component
public class Transport {
	
	// 버스
	public String getBus(int num) {
		System.out.println("버스");
		return "Bus";
	}
	
	// 지하철
	public String getSubway(String name) {
		System.out.println("지하철");
		return "Subway";
	}
	
	// 택시
	
	// 자전거
	public String bicycle(int money) {
		System.out.println("자전거 사용");
		return "Seoul";
	}
	
	// 도보
	public void walk() {
		System.out.println("도보");
	}

}
