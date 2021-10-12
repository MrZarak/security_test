package com.zarak.spring_security_test.dto.user;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
	private final String login;
	private final String email;
	private final int age;
	private final double money;
	private final UserRole role;
}
