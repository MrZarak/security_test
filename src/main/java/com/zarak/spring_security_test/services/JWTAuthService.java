package com.zarak.spring_security_test.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.JWTProcessor;
import com.zarak.spring_security_test.config.auth.JWTAuthenticationToken;
import com.zarak.spring_security_test.dto.user.UserDto;
import com.zarak.spring_security_test.dto.user.UserRole;
import com.zarak.spring_security_test.mapper.UserMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JWTAuthService implements AuthService {
	private static final byte[] secretKey = new byte[32];

	static {
		Random random = new Random(2021);
		for(int i = 0; i < secretKey.length; i++) {
			secretKey[i] = (byte) random.nextInt(128);
		}
	}

	private JWTProcessor<SimpleSecurityContext> processor;
	private UserService service;


	@Override
	public Authentication authenticate(HttpServletRequest request) {
		String token = parseToken(request.getHeader("Authorization"));
		if(token != null) {
			try {
				final JWTClaimsSet claimsSet = processor.process(token, null);
				String username = getClaim(claimsSet, "username");
				service.findByName(username).ifPresent(userJPA -> {
					UserDto userDto = UserMapper.INSTANCE.toDto(userJPA);
					final UserDetails details = makeDetails(userDto.getLogin(), userDto.getRole());
					Authentication authentication = new JWTAuthenticationToken(details, userDto, token);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				});
			} catch(java.text.ParseException e) {
				System.out.println("Try log in invalid token: " + token);
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

	private UserDetails makeDetails(String name, UserRole... roles) {
		return new UserDetails() {
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return Arrays.stream(roles).map(Enum::name).map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableList());
			}

			@Override
			public String getPassword() {
				return null;
			}

			@Override
			public String getUsername() {
				return name;
			}

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isEnabled() {
				return true;
			}
		};
	}

	private String getClaim(JWTClaimsSet claims, String fieldName) {
		Object value = claims.getClaims().get(fieldName);
		if(value == null) {
			throw new IllegalArgumentException(String.format("Claim '%s' is null", fieldName));
		}
		return value.toString();
	}


}
