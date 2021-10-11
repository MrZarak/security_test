package com.zarak.spring_security_test.services;

import java.util.Optional;

import com.zarak.spring_security_test.jpa.model.UserJPA;

public interface UserService {
	Optional<UserJPA> findByName(String name);
	UserJPA saveUser(UserJPA userJPA);
}
