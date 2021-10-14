package com.zarak.spring_security_test.mapper;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserDto.UserDtoBuilder;
import com.zarak.spring_security_test.dto.user.UserInputDto;
import com.zarak.spring_security_test.dto.user.UserPswdDto;
import com.zarak.spring_security_test.dto.user.UserPswdDto.UserPswdDtoBuilder;
import com.zarak.spring_security_test.jpa.model.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-14T01:13:08+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 16.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.login( user.getUsername() );
        userDto.email( user.getEmail() );
        userDto.age( user.getAge() );
        userDto.money( user.getMoney() );
        userDto.role( user.getRole() );

        return userDto.build();
    }

    @Override
    public UserDto toDto(UserPswdDto user) {
        if ( user == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.login( user.getLogin() );
        userDto.email( user.getEmail() );
        userDto.age( user.getAge() );
        userDto.money( user.getMoney() );
        userDto.role( user.getRole() );

        return userDto.build();
    }

    @Override
    public UserPswdDto toDtoPswd(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserPswdDtoBuilder userPswdDto = UserPswdDto.builder();

        userPswdDto.login( user.getUsername() );
        userPswdDto.email( user.getEmail() );
        userPswdDto.age( user.getAge() );
        userPswdDto.password( user.getPassword() );
        userPswdDto.money( user.getMoney() );
        userPswdDto.role( user.getRole() );

        return userPswdDto.build();
    }

    @Override
    public UserEntity toEntity(UserPswdDto user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( user.getLogin() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setAge( user.getAge() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setMoney( user.getMoney() );
        userEntity.setRole( user.getRole() );

        return userEntity;
    }

    @Override
    public UserEntity toEntity(UserInputDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( dto.getLogin() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setAge( dto.getAge() );
        userEntity.setPassword( dto.getPassword() );

        userEntity.setMoney( 0.0 );
        userEntity.setRole( com.zarak.spring_security_test.dto.user.UserRole.USER );

        return userEntity;
    }
}
