package com.zarak.spring_security_test.service;

import java.util.Optional;

import com.zarak.spring_security_test.dto.user.UserPswdDto;

public interface UserService {
	Optional<UserPswdDto> findByName(String name);
	UserPswdDto saveUser(UserPswdDto userDto);
}
