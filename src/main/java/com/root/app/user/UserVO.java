package com.root.app.user;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserVO {
	
	private String username;
	private String password;
	private String name;
	private String phone;
	private String email;
	private Date birth;
	private String oriName;
	private String fileName;

}
