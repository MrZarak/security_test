package com.zarak.spring_security_test.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.zarak.spring_security_test.config.auth.JWTAuthenticationToken;
import com.zarak.spring_security_test.dto.user.UserPswdDto;

public class SecurityContextUtils {
	private SecurityContextUtils() {

	}

	public static JWTAuthenticationToken getAuthentication() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof JWTAuthenticationToken) {
			return (JWTAuthenticationToken) authentication;
		}
		return null;
	}

	public static Optional<UserPswdDto> getUser() {
		final Optional<JWTAuthenticationToken> authentication = Optional.ofNullable(getAuthentication());
		return authentication.map(JWTAuthenticationToken::getDto);
	}
}
