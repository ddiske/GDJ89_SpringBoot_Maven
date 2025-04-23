package com.root.app.user;

import java.sql.Date;
import java.util.List;

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
public class UserVO {
	
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

}
