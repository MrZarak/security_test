package com.zarak.spring_security_test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zarak.spring_security_test.dto.user.MsgDto;
import com.zarak.spring_security_test.dto.user.UserPswdDto;
import com.zarak.spring_security_test.util.SecurityContextUtils;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicController {

	@GetMapping("/info")
	public MsgDto publicInfo() {
		String login = SecurityContextUtils.getUser().map(UserPswdDto::getLogin).orElse("No auth");
		return new MsgDto("Some public text for -> " + login);
	}
}
