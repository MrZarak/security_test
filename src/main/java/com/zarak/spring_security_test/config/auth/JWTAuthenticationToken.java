package com.zarak.spring_security_test.config.auth;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserPswdDto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JWTAuthenticationToken extends AbstractAuthenticationToken {
	private final UserDetails details;
	private final UserPswdDto dto;
	private final String token;

	public JWTAuthenticationToken(UserDetails details, UserPswdDto dto, String token) {
		super(details.getAuthorities());
		this.details = details;
		this.dto = dto;
		this.token = token;
		super.setAuthenticated(true);
	}


	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public UserPswdDto getPrincipal() {
		return dto;
	}
}
