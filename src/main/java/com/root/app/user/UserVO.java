package com.root.app.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserVO implements UserDetails, OAuth2User {
	
	@NotBlank(message = "ID는 필수", groups = JoinGroup.class)
	private String username;
	@Size(min = 8, max = 16)
	@NotBlank(groups = JoinGroup.class)
	private String password;
	private String passwordCheck;
	@NotBlank(groups = {UpdateGroup.class, JoinGroup.class})
	private String name;
	@NotBlank(groups = {UpdateGroup.class, JoinGroup.class})
	private String phone;
	@Email(groups = {UpdateGroup.class, JoinGroup.class})
	@NotBlank(groups = {UpdateGroup.class, JoinGroup.class})
	private String email;
	@Past(groups = {UpdateGroup.class, JoinGroup.class})
	private Date birth;
	private String oriName;
	private String fileName;
	private List<RoleVO> list;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// ROLE_NAME을 리턴
		List<GrantedAuthority> ar = new ArrayList<>();
		
		for(RoleVO vo : this.list) {
			GrantedAuthority g = new SimpleGrantedAuthority(vo.getRoleName());
			ar.add(g);
		}
		
		return ar;
	}

	
	/** OAuth2User */
	private String accessToken;
	private String sns;
	
	private Map<String, Object> attributes;
//	@Override
//	public Map<String, Object> getAttributes() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	/** UserDetails */
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	// ID가 존재하지 않는 경우
	// InternalAuthenticationServiceException: UserDetailsService returned null, which is an interface contract violation
	
	// 비밀번호가 틀린 경우
	// BadCredentialsException: 자격 증명에 실패하였습니다.
	
//	@Override
//	public boolean isAccountNonExpired() {
//		// false면 AccountExpiredException: 사용자 계정의 유효 기간이 만료 되었습니다.
//		return true;
//	}
//	@Override
//	public boolean isAccountNonLocked() {
//		// false면 LockedException: 사용자 계정이 잠겨 있습니다.
//		return true;
//	}
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// false면 CredentialsExpiredException: 자격 증명 유효 기간이 만료되었습니다.
//		return true;
//	}
//	@Override
//	public boolean isEnabled() {
//		// false면 DisabledException: 유효하지 않은 사용자입니다.
//		return true;
//	}
	
}
