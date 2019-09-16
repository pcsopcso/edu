package org.client.repository;

import org.client.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
	public UserInfo findByUsername(String username);
}
