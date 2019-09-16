package org.authorizationserver.repository;

import org.authorizationserver.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

	public UserInfo findByUsername(String username);

}
