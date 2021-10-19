package com.zarak.spring_security_test.config.properties;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
	@NotNull
	private String secret;
}
