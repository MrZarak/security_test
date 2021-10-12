package com.zarak.spring_security_test.integrated;

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
import com.zarak.spring_security_test.mapper.UserMapper;
import com.zarak.spring_security_test.mock.WithMockCustomUser;
import com.zarak.spring_security_test.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class AdminComponentTests {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@SpyBean
	private UserService userService;

	@Autowired
	private MockMvc mvc;

	@WithMockCustomUser
	@Test
	void forbiddenWhenNoAdmin() throws Exception {
		var userInputDto = prepareTestUserInputDto();

		doReturn(Optional.empty()).when(userService).saveUser(any());

		mvc.perform(post("/admin/get_or_create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userInputDto)))
				.andExpect(status().isForbidden())
				.andDo(print());
	}

	@WithMockCustomUser(authority = UserRole.ADMIN)
	@Test
	void getOrCreateUserTest() throws Exception {
		var userInputDto = prepareTestUserInputDto();
		var userPswdDto = UserPswdDto.builder()
				.login(userInputDto.getLogin())
				.email(userInputDto.getEmail())
				.build();

		doReturn(Optional.of(userPswdDto)).when(userService).saveUser(any());

		mvc.perform(post("/admin/get_or_create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userInputDto)))
				.andExpect(status().isOk())
				.andDo(print());
	}

	private UserInputDto prepareTestUserInputDto() {
		return UserInputDto.builder()
				.email("gfdsgsd@mail.com")
				.login("Bobus")
				.password("Kek_LOL")
				.age(18)
				.build();
	}
}
