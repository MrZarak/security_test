package com.zarak.spring_security_test.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface AuthService {
	Authentication authenticate(HttpServletRequest request);
}
