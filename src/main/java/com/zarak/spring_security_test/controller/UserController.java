package com.zarak.spring_security_test.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.mapper.UserMapper;
import com.zarak.spring_security_test.util.SecurityContextUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	private final UserMapper mapper;

	@GetMapping("/me")
	public Optional<UserDto> me() {
		return SecurityContextUtils.getUser().map(mapper::toDto);
	}
}
