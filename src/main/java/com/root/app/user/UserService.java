package com.root.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.root.app.files.FileManager;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.files.base}")
	private String path;
	
	private String kind = "user";
	
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
		return userDAO.join(userVO);
	}
	
	public UserVO login(UserVO userVO) throws Exception {
		
		return userDAO.login(userVO);
	}

}
