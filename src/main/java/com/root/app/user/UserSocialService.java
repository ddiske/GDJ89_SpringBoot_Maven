package com.root.app.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserSocialService extends DefaultOAuth2UserService {
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		ClientRegistration clientRegistration = userRequest.getClientRegistration();
		String sns = clientRegistration.getRegistrationId();
		if(sns.equalsIgnoreCase("kakao")) {
			return this.useKakao(userRequest);
		}
		
		
		return null;
	}
	
	private OAuth2User useKakao(OAuth2UserRequest userRequest) {
		
		// ID로 DB에서 조회
		// 데이터가 없다면 DB에 INSERT
		// 아니라면 조회한 데이터로 작성
		
		OAuth2User user = super.loadUser(userRequest);
		log.info("{}", user);
		log.info("{}", user.getName());
		log.info("{}", user.getAttributes());
		log.info("{}", user.getAuthorities());
		
		Map<String, Object> attr = (Map<String, Object>)user.getAttributes().get("properties");
		
		UserVO userVO = new UserVO();
		userVO.setAttributes(user.getAttributes());
		userVO.setUsername(attr.get("nickname").toString());
		userVO.setFileName(attr.get("thumbnail_image").toString());
		userVO.setAccessToken(userRequest.getAccessToken().getTokenValue());
		userVO.setSns(userRequest.getClientRegistration().getRegistrationId());
		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");
		list.add(roleVO);
		userVO.setList(list);
		
		return userVO;
	}

}
