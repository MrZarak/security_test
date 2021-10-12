package com.zarak.spring_security_test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserInputDto;
import com.zarak.spring_security_test.dto.user.UserPswdDto;
import com.zarak.spring_security_test.jpa.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(source = "user.username", target = "login")
	@Mapping(source = "user.email", target = "email")
	@Mapping(source = "user.age", target = "age")
	@Mapping(source = "user.money", target = "money")
	@Mapping(source = "user.role", target = "role")
	UserDto toDto(UserEntity user);

	@Mapping(source = "user.login", target = "login")
	@Mapping(source = "user.email", target = "email")
	@Mapping(source = "user.age", target = "age")
	@Mapping(source = "user.money", target = "money")
	@Mapping(source = "user.role", target = "role")
	UserDto toDto(UserPswdDto user);

	@Mapping(source = "user.username", target = "login")
	@Mapping(source = "user.email", target = "email")
	@Mapping(source = "user.age", target = "age")
	@Mapping(source = "user.password", target = "password")
	@Mapping(source = "user.money", target = "money")
	@Mapping(source = "user.role", target = "role")
	UserPswdDto toDtoPswd(UserEntity user);


	@Mapping(source = "user.login", target = "username")
	@Mapping(source = "user.email", target = "email")
	@Mapping(source = "user.age", target = "age")
	@Mapping(source = "user.password", target = "password")
	@Mapping(source = "user.money", target = "money")
	@Mapping(source = "user.role", target = "role")
	UserEntity toEntity(UserPswdDto user);

	@Mapping(source = "dto.login", target = "username")
	@Mapping(source = "dto.email", target = "email")
	@Mapping(source = "dto.age", target = "age")
	@Mapping(source = "dto.password", target = "password")
	@Mapping(constant = "0.0", target = "money")
	@Mapping(expression = "java(com.zarak.spring_security_test.dto.user.UserRole.USER)", target = "role")
	UserEntity toEntity(UserInputDto dto);
}
