package com.root.app.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 순서는 registry가 등록된 순서
		registry.addInterceptor(new TestInterceptor())
				.addPathPatterns("/notice/*")
				.excludePathPatterns("/notice/detail");
		
	}

}
