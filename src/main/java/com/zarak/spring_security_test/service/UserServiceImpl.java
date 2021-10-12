package com.zarak.spring_security_test.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zarak.spring_security_test.dto.user.UserPswdDto;
import com.zarak.spring_security_test.jpa.repository.UserEntityRepository;
import com.zarak.spring_security_test.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserEntityRepository repository;
	private final UserMapper mapper;

	@Override
	public Optional<UserPswdDto> findByName(String name) {
		return repository.findByUsername(name).map(mapper::toDtoPswd);
	}

	@Transactional
	@Override
	public Optional<UserPswdDto> saveUser(UserPswdDto userDto) {
		return Optional.of(repository.save(mapper.toEntity(userDto))).map(mapper::toDtoPswd);
	}
}
