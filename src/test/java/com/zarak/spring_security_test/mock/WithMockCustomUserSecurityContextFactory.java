package com.zarak.spring_security_test.mock;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

import com.zarak.spring_security_test.config.auth.JWTAuthenticationToken;
import com.zarak.spring_security_test.dto.user.UserPswdDto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

	@Override
	public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(customUser.authority().getAuthority()));

		User securityUser = new User(customUser.username(), customUser.password(), authorities);
		UserPswdDto dto = UserPswdDto.builder()
				.login(customUser.username())
				.email(customUser.email())
				.password(customUser.password())
				.build();
		context.setAuthentication(new JWTAuthenticationToken(securityUser, dto, null));
		return context;
	}

}