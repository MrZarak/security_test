package com.zarak.spring_security_test.service;

import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.JWTProcessor;
import com.zarak.spring_security_test.config.auth.JWTAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class JWTAuthService implements AuthService {

	private JWTProcessor<SimpleSecurityContext> processor;
	private UserService service;

	@Override
	public Authentication authenticate(HttpServletRequest request) {
		String token = parseToken(request.getHeader("Authorization"));
		if(token != null) {
			try {
				JWTClaimsSet claimsSet = processor.process(token, null);
				String username = getClaim(claimsSet, "username");
				Optional<Authentication> authenticationOptional = service.findByName(username).map(userPswdDto -> {
					UserDetails details = new User(userPswdDto.getLogin(), userPswdDto.getPassword(),
							Collections.singleton(new SimpleGrantedAuthority(userPswdDto.getRole().getAuthority())));
					return new JWTAuthenticationToken(details, userPswdDto, token);
				});
				if(authenticationOptional.isPresent()) {
					return authenticationOptional.get();
				}
			} catch(java.text.ParseException | BadJOSEException e) {
				log.info("Try log in invalid token: " + token);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private String parseToken(String in) {
		if(in == null)
			return null;

		if(in.startsWith("Bearer "))
			return in.substring("Bearer ".length());

		return in;
	}

	private String getClaim(JWTClaimsSet claims, String fieldName) {
		Object value = claims.getClaims().get(fieldName);
		if(value == null) {
			throw new IllegalArgumentException(String.format("Claim '%s' is null", fieldName));
		}
		return value.toString();
	}
}
