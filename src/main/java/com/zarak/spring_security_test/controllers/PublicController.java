package com.zarak.spring_security_test.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zarak.spring_security_test.config.auth.JWTAuthenticationToken;
import com.zarak.spring_security_test.dto.user.MsgDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicController {

	@GetMapping("/info")
	public MsgDto publicInfo() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String additionalText;
		if(authentication instanceof JWTAuthenticationToken) {
			additionalText = ((JWTAuthenticationToken) authentication).getDto().getLogin();
		} else {
			additionalText = "no auth";
		}
		return new MsgDto("Some public text for " + additionalText);
	}
}
