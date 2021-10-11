package com.zarak.spring_security_test.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zarak.spring_security_test.config.auth.JWTAuthenticationToken;
import com.zarak.spring_security_test.dto.user.UserDto;

@RestController
@RequestMapping("/user")
public class UserController {
	@GetMapping("/me")
	public UserDto me() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof JWTAuthenticationToken) {
			return ((JWTAuthenticationToken) authentication).getDto();
		}
		return null;//never be reached
	}
}
