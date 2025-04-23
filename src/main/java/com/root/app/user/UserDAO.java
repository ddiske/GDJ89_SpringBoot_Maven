package com.root.app.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Mapper
public interface UserDAO {
	
	public int join(UserVO userVO) throws Exception;
	public UserVO getDetail(UserVO userVO) throws UsernameNotFoundException;
	public int update(UserVO userVO) throws Exception;

}
