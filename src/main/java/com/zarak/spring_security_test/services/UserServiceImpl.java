package com.zarak.spring_security_test.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zarak.spring_security_test.jpa.model.UserJPA;
import com.zarak.spring_security_test.jpa.repository.UserJPARepository;

import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserJPARepository repository ;

	@Override
	public Optional<UserJPA> findByName(String name) {
		return repository.findByUsername(name);
	}

	@Transactional
	@Override
	public UserJPA saveUser(UserJPA userJPA) {
		return repository.save(userJPA);
	}
}
