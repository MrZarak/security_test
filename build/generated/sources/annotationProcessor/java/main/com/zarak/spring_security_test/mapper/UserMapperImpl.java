package com.zarak.spring_security_test.mapper;

import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserInputDto;
import com.zarak.spring_security_test.dto.user.UserPswdDto;
import com.zarak.spring_security_test.dto.user.UserRole;
import com.zarak.spring_security_test.jpa.model.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-13T18:39:59+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.2.jar, environment: Java 16.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        String login = null;
        String email = null;
        int age = 0;
        double money = 0.0d;
        UserRole role = null;

        login = user.getUsername();
        email = user.getEmail();
        age = user.getAge();
        money = user.getMoney();
        role = user.getRole();

        UserDto userDto = new UserDto( login, email, age, money, role );

        return userDto;
    }

    @Override
    public UserDto toDto(UserPswdDto user) {
        if ( user == null ) {
            return null;
        }

        String login = null;
        String email = null;
        int age = 0;
        double money = 0.0d;
        UserRole role = null;

        login = user.getLogin();
        email = user.getEmail();
        age = user.getAge();
        money = user.getMoney();
        role = user.getRole();

        UserDto userDto = new UserDto( login, email, age, money, role );

        return userDto;
    }

    @Override
    public UserPswdDto toDtoPswd(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        String login = null;
        String email = null;
        int age = 0;
        String password = null;
        double money = 0.0d;
        UserRole role = null;

        login = user.getUsername();
        email = user.getEmail();
        age = user.getAge();
        password = user.getPassword();
        money = user.getMoney();
        role = user.getRole();

        UserPswdDto userPswdDto = new UserPswdDto( login, password, email, age, money, role );

        return userPswdDto;
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
