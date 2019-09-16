package org.authorizationserver.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.authorizationserver.constrant.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

@ToString
@Entity
@AllArgsConstructor //추가 모든 필드가 있는 생성자를 만든다.
@NoArgsConstructor(access = AccessLevel.PUBLIC) 
@Table(name = "USER_INFO")//Resource owner의 정보
public class UserInfo implements UserDetails{

	private static final long serialVersionUID = -3699911426704150861L;

	@Id
	@Getter@Setter
	private Long id;
	
	@Getter@Setter
	private String username;
	
	@Column(length=400)
	@Getter@Setter
	private String password;
	
	@Column@Enumerated(EnumType.STRING)
	@Getter@Setter
	private UserRole role;
	
	@Transient
	@Getter@Setter
	private Collection<? extends GrantedAuthority> authorities;
	
	@Getter@Setter
	private boolean accountNonExpired = true;
	
	@Getter@Setter
	private boolean accountNonLocked = true;

	@Getter@Setter
	private boolean credentialsNonExpired = true;
	
	@Getter@Setter
	private boolean enabled = true;


}
