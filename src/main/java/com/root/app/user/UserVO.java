package com.root.app.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class UserVO implements UserDetails {
	
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
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
