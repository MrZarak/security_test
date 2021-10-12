package com.zarak.spring_security_test.service;

import java.util.Optional;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserInputDto;

public interface AdminService {
	Optional<UserDto> getOrCreateUser(UserInputDto dto);
}
