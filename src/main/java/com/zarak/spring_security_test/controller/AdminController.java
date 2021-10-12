package com.zarak.spring_security_test.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserInputDto;
import com.zarak.spring_security_test.service.AdminService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
	private final AdminService adminService;

	@PostMapping("/get_or_create")
	public Optional<UserDto> getOrCreate(@RequestBody UserInputDto inputDto) {
		return adminService.getOrCreateUser(inputDto);
	}
}
