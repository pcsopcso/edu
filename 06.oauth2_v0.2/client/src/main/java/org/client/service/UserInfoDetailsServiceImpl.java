package org.client.service;

import org.client.entity.UserInfo;
import org.client.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserInfoDetailsServiceImpl implements UserDetailsService{
	
	@Autowired private UserInfoRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("UserDetailsServiceImpl.loadUserByUsername :::: {}",username);
		
		UserInfo user = repository.findByUsername(username);
		
		if(ObjectUtils.isEmpty(user)) {
			throw new UsernameNotFoundException("Invalid username, please check user info !");
		}
		
		user.setAuthorities(AuthorityUtils.createAuthorityList(String.valueOf(user.getRole())));
		
		return user;
	}
	
}
