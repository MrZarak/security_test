package com.zarak.spring_security_test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.jpa.model.UserJPA;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "user.username", target = "login")
	@Mapping(source = "user.email", target = "email")
	@Mapping(source = "user.age", target = "age")
	@Mapping(source = "user.money", target = "money")
	@Mapping(source = "user.role", target = "role")
	UserDto toDto(UserJPA user);
}
