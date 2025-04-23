package com.root.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.root.app.files.FileManager;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${app.files.base}")
	private String path;
	
	private String kind = "user";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userVO = new UserVO();
		userVO.setUsername(username);
		userVO = userDAO.getDetail(userVO);
		return userVO;
	}
	
	public boolean userErrorCheck(UserVO userVO, BindingResult bindingResult) throws Exception {
		// return true면 검증 실패
		// return false면 검증 통과
		boolean check = false;
		check = bindingResult.hasErrors();
		
		// password가 일치하는 지 검증
		if(!userVO.getPassword().equals(userVO.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck", "userVO.password.notEqual");
		}
		
		// ID 중복 검사
		UserVO checkVO = userDAO.getDetail(userVO);
		if(checkVO != null) {
			check = true;
			bindingResult.rejectValue("username", "userVO.username.equal");
		}
		
		return check;
		
	}
	
	public int join(UserVO userVO, MultipartFile multipartFile) throws Exception {
		if(multipartFile.getSize() != 0) {
			userVO.setFileName(fileManager.fileSave(path.concat(kind), multipartFile));
			userVO.setOriName(multipartFile.getOriginalFilename());
		}
		userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));
		return userDAO.join(userVO);
	}
	
	public int update(UserVO userVO) throws Exception {
		return userDAO.update(userVO);
	}

}
