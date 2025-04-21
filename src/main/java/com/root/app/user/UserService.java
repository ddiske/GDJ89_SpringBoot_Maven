package com.root.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
