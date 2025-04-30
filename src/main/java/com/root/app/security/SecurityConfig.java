package com.root.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.root.app.security.jwt.JwtAuthenticationFilter;
import com.root.app.security.jwt.JwtLoginFilter;
import com.root.app.security.jwt.JwtTokenManager;
import com.root.app.user.UserService;
import com.root.app.user.UserSocialService;

@Configuration
@EnableWebSecurity//(debug = true)
public class SecurityConfig  {
	
	@Autowired
	private AuthenticationConfiguration configuration;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
//	@Autowired
//	private SecurityLoginSuccessHandler handler;
//	
//	@Autowired
//	private SecurityLoginFailHandler failHandler;
	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private UserSocialService service;
//	
//	@Autowired
//	private SecurityLogoutHandler logoutHandler;
	
	// 정적자원들을 Security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		return (web) -> {
			web.ignoring()
			   .requestMatchers("/css/**")
			   .requestMatchers("/js/**", "/img/**", "/vendor/**", "/images/**");
		};
	};
	
	// 인증과 권한에 관한 설정
	@Bean
	SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.cors(cors->cors.disable()) /** CORS 허용, Filter에서 사용 가능 */
					.csrf(csrf->csrf.disable())
					/** 권한 적용 */
					.authorizeHttpRequests(authorizeRequest->{
						authorizeRequest.requestMatchers("/notice/add", "/notice/update", "/notice/delete")
										.hasRole("ADMIN")
										.requestMatchers("/user/mypage", "/user/logout")
										.authenticated()
										.requestMatchers("/manager/**")
										.hasAnyRole("MANAGER", "ADMIN")
										.anyRequest()
										.permitAll();
					})
					/** Form 관련 설정 */
					.formLogin(formLogin->{
						formLogin//.loginPage("/user/login")
//								 .usernameParameter("id")
//						 		 .passwordParameter("pw")
//								 .defaultSuccessUrl("/")
//								 .successHandler(handler)
//								 .failureUrl("/user/login")
//								 .failureHandler(failHandler)
//								 .permitAll();
						.disable();
					})
					/** Logout 관련 설정 */
//					.logout(logout->{
//						logout.logoutUrl("/user/logout")
////							  .logoutSuccessUrl("/")
//							  .addLogoutHandler(logoutHandler)
//							  .invalidateHttpSession(true)
//							  .permitAll();
//					})
					/** Remember me */
//					.rememberMe(rememberme->{
//						rememberme.rememberMeParameter("remember-me")
//								  .tokenValiditySeconds(60)
//								  .key("rememberkey")
//								  .userDetailsService(userService)
//								  .authenticationSuccessHandler(handler)
//								  .useSecureCookie(false);
//					})
					// 동시접속 제한
//					.sessionManagement(s->{
//						s.invalidSessionUrl("/")
//						 .sessionFixation().changeSessionId()//.newSession()//.none
//						 .maximumSessions(1)
//						 .maxSessionsPreventsLogin(false)
//						 .expiredUrl("/");
//						 
//					})
//					.oauth2Login(oauth2Login->{
//						oauth2Login.userInfoEndpoint(user->{
//							user.userService(service);
//						});
//					})
					/** 
					 *  Token 인증 방식
					 *  JWT Token으로 로그인시 클라이언트에서 Token 값을 서버에 전달하지 않더라도 로그인 가능
					 **/
					.sessionManagement(s->{
						s.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
					})
					/**
					 *  쿠키와 세션을 이용하는 방식이 아닌 Request Header에 ID, PW를 직접 보내는 방식으로 보안에 취약
					 *  HTTP Basic 방식은 해제
					 */
					.httpBasic(h->{
						h.disable();
					})
					.addFilter(new JwtLoginFilter(configuration.getAuthenticationManager(), jwtTokenManager))
					.addFilter(new JwtAuthenticationFilter(configuration.getAuthenticationManager(), jwtTokenManager))
					;
		
					
		
		return httpSecurity.build();
	}

}
