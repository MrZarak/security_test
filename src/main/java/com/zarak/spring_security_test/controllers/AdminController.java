package com.zarak.spring_security_test.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserInputDto;
import com.zarak.spring_security_test.dto.user.UserRole;
import com.zarak.spring_security_test.jpa.model.UserJPA;
import com.zarak.spring_security_test.mapper.UserMapper;
import com.zarak.spring_security_test.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
	private final PasswordEncoder encoder;
	private final UserService userService;

	@PostMapping("/create_user")
	public UserDto adminGetOrCreate(@RequestBody UserInputDto inputDto) {
		UserDto userDto;

		UserJPA jpaUser = userService.findByName(inputDto.getLogin()).orElse(null);
		if(jpaUser == null) {
			String password = encoder.encode(inputDto.getPassword());
			jpaUser = new UserJPA(0L, inputDto.getLogin(), inputDto.getEmail(), password, inputDto.getAge(), 0.0, UserRole.USER);
			System.out.println("Created user: " + inputDto);
		}
		userDto = UserMapper.INSTANCE.toDto(userService.saveUser(jpaUser));
		return userDto;
	}
}
