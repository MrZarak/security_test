package com.zarak.spring_security_test.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserInputDto;
import com.zarak.spring_security_test.dto.user.UserPswdDto;
import com.zarak.spring_security_test.dto.user.UserRole;
import com.zarak.spring_security_test.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
	private final PasswordEncoder encoder;
	private final UserMapper userMapper;
	private final UserService userService;

	@Override
	public Optional<UserDto> getOrCreateUser(UserInputDto inputDto) {
		System.out.println(encoder.encode("1q2w3e4r"));
		log.info("Try get or create user: " + inputDto);
		final Optional<UserPswdDto> byName = userService.findByName(inputDto.getLogin());
		if(byName.isEmpty()) {
			String password = encoder.encode(inputDto.getPassword());
			var userEntity = new UserPswdDto(inputDto.getLogin(), password, inputDto.getEmail(), inputDto.getAge(), 0.0, UserRole.USER);
			userService.saveUser(userEntity);
			log.info("Created user: " + inputDto);
			return Optional.of(userEntity).map(userMapper::toDto);
		} else {
			log.info("Already exists user: " + inputDto);
			return byName.map(userMapper::toDto);
		}
	}
}
