package com.zarak.spring_security_test.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.jwt.proc.JWTProcessor;
import com.zarak.spring_security_test.config.properties.JwtProperties;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
class JwtConfig {
	private final JwtProperties properties;

	@Bean
	public JWTProcessor<SimpleSecurityContext> processor() {
		DefaultJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
		JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<>(properties.getSecret().getBytes(StandardCharsets.UTF_8));
		JWEKeySelector<SimpleSecurityContext> jweKeySelector =
				new JWEDecryptionKeySelector<>(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
		(jwtProcessor).setJWEKeySelector(jweKeySelector);
		return jwtProcessor;
	}
}