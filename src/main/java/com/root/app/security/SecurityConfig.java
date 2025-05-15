package com.root.app.security;

import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.root.app.security.jwt.JwtLoginFilter;
import com.root.app.security.jwt.JwtTokenManager;
import com.root.app.user.UserService;
import com.root.app.user.UserSocialService;

@Configuration
@EnableWebSecurity//(debug = true)
public class SecurityConfig  {
	
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSocialService service;
	
	@Autowired
	private SecurityLogoutHandler logoutHandler;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	// 정적자원들을 Security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		return (web) -> {
			web.ignoring()
			   .requestMatchers("/css/**")
			   .requestMatchers("/js/**", "/img/**", "/vendor/**", "/images/**");
		};
	};
	
	
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		
		// Get 메서드까지 허용
		corsConfiguration.setAllowedOrigins(List.of("*"));
		
		// 메서드 추가 허용
		corsConfiguration.setAllowedMethods(List.of("POST", "DELETE", "PATCH", "PUT", "GET"));
		
		corsConfiguration.setAllowedHeaders(List.of("Content-type"));
		corsConfiguration.setExposedHeaders(List.of("AccessToken", "RefreshToken"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	
	// 인증과 권한에 관한 설정
	@Bean
	SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.cors(cors->cors.configurationSource(this.corsConfigurationSource())) /** CORS 허용, Filter에서 사용 가능 */
					.csrf(csrf->csrf.disable())
					/** 권한 적용 */
					.authorizeHttpRequests(authorizeRequest->{
						authorizeRequest//.requestMatchers("/notice/add", "/notice/update", "/notice/delete")
//										.hasRole("ADMIN")
//										.requestMatchers("/user/mypage", "/user/logout")
//										.authenticated()
//										.requestMatchers("/manager/**")
//										.hasAnyRole("MANAGER", "ADMIN")
										.anyRequest()
										.permitAll();
					})
					/** Form 관련 설정 */
					.formLogin(formLogin->{
						formLogin.disable();
//								 .loginPage("/user/login")
////								 .usernameParameter("id")
////						 		 .passwordParameter("pw")
////								 .defaultSuccessUrl("/")
//								 .successHandler(handler)
////								 .failureUrl("/user/login")
//								 .failureHandler(failHandler)
//								 .permitAll();
					})
					/** Logout 관련 설정 */
					.logout(logout->{
						logout.disable();
//							  .logoutUrl("/user/logout")
////							  .logoutSuccessUrl("/")
//							  .addLogoutHandler(logoutHandler)
//							  .invalidateHttpSession(true)
//							  .permitAll();
					})
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
					.sessionManagement(s->{
						s.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//						 .invalidSessionUrl("/")
//						 .sessionFixation().changeSessionId()//.newSession()//.none
//						 .maximumSessions(1)
//						 .maxSessionsPreventsLogin(false)
//						 .expiredUrl("/");
						 
					})
					.oauth2Login(oauth2Login->{
						oauth2Login.userInfoEndpoint(user->{
							user.userService(service);
						});
					})
					.httpBasic(httpBasic -> httpBasic.disable())
					.addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
					;
		
					
		
		return httpSecurity.build();
	}

}
