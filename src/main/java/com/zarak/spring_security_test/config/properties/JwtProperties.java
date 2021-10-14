package com.zarak.spring_security_test.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
	// TODO: add not-null validation
	// server start should fail if secret not specified
	private String secret;
}
