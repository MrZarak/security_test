package com.zarak.spring_security_test.mock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

import com.zarak.spring_security_test.dto.user.UserRole;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

	String username() default "Zarak";

	String email() default "mrzaraj0303@gmail.com";

	String password() default "1q2w3e";

	UserRole authority() default UserRole.USER;
}
