package com.root.app.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

    // 1. Filter 객체 생성
    // 2. Filter 등록
    // 3. 순서 지정
    // 4. URL Mapping
    // <bean></bean>
    @Bean
    FilterRegistrationBean<Filter> filterRegistrationBean() {
		
		FilterRegistrationBean<Filter> filBean = new FilterRegistrationBean<>();
		filBean.setFilter(new TestFilter());
		filBean.setOrder(1);
//		filBean.addUrlPatterns("/user/login", "/notice/list");
		filBean.addUrlPatterns("/notice/*");
		
		return filBean;
		
	}
    
    @Bean
    FilterRegistrationBean<Filter> filterRegistrationBean2() {
    	FilterRegistrationBean<Filter> filBean = new FilterRegistrationBean<>();
    	filBean.setFilter(new NoticeFilter());
    	filBean.setOrder(2);
    	filBean.addUrlPatterns("/notice/detail");
    	
    	return filBean;
    }

}
