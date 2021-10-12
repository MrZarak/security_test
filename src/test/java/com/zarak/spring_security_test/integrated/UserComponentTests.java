package com.zarak.spring_security_test.integrated;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zarak.spring_security_test.dto.user.UserInputDto;
import com.zarak.spring_security_test.dto.user.UserPswdDto;
import com.zarak.spring_security_test.dto.user.UserRole;
import com.zarak.spring_security_test.mock.WithMockCustomUser;
import com.zarak.spring_security_test.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class UserComponentTests {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@SpyBean
	private UserService userService;

	@Autowired
	private MockMvc mvc;

	@Test
	void meNoAuth() throws Exception {
		mvc.perform(get("/user/me")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden())
				.andDo(print());
	}

	@WithMockCustomUser(authority = UserRole.USER)
	@Test
	void me() throws Exception {
		mvc.perform(get("/user/me")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
}
