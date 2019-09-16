package org.authorizationserver.service;

import org.authorizationserver.entity.UserInfo;
import org.authorizationserver.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;


/**
 * UserDetailsService 구현체. 
 * UserInfo 인증에 사용되는 서비스클래스이다.
 * @author daniel Park
 *
 */
@Slf4j
@Service
public class UserInfoDetailsServiceImpl implements UserDetailsService {
	
	@Autowired private UserInfoRepository resposiotry;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("UserInfoDetailsServiceImpl.loadUserByUsername :::: {}",username);
		
		UserInfo user = resposiotry.findByUsername(username);
		
		if(ObjectUtils.isEmpty(user)) {
			throw new UsernameNotFoundException("Invalid resource owner, please check resource owner info !");
		}
		
		user.setAuthorities(AuthorityUtils.createAuthorityList(String.valueOf(user.getRole())));
		
		return user;
	}

}
