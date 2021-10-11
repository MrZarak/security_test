package com.zarak.spring_security_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
class MVCConfig {
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}