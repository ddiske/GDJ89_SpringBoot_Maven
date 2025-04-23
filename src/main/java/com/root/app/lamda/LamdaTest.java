package com.root.app.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class LamdaTest {
	
	public void test3() {
		
		Supplier<List<String>> s = () -> {
			String [] n = {"a", "b", "c"};
			List<String> ar = Arrays.asList(n);
			
			return ar;
		};
		
	}
	
	public void test2() {
		
		// interface에 두개 이상의 메서드가 있는 경우는 Lamda식을 사용할 수 없음
//		Test2Interface t = (int t1) -> {
//			
//		};
		
		Test3Interface t3 = (List<Object> ar) -> {
			System.out.println("test");
		};
		
		t3.t1(null);
		
	}
	
	public void test() {
		TestInterface t = new Plus();
		t.cal(10, 20);
		
		t = new Minus();
		t.cal(10, 20);
		
		t = (int n1, int n2) -> {
			return n1 + n2;
		};
		int result = t.cal(10, 20);
		
		t = (int n1, int n2) -> {
			return n1 * n2;
		};
		result = t.cal(1, 2);
	}

}
