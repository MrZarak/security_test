package com.zarak.spring_security_test.dto.user;


import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserPswdDto  {
	private final String login;
	private final String password;
	private final String email;
	private final int age;
	private final double money;
	private final UserRole role;
}
