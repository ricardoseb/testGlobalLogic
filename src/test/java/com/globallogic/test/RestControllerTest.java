package com.globallogic.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.test.controller.UserRestController;
import com.globallogic.test.entity.Phone;
import com.globallogic.test.entity.User;

import com.globallogic.test.service.IUserService;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserRestController.class)
public class RestControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	
	@MockBean
	IUserService iUserService;
	
	
	
	
	@Test
	public void createUserWithHttpStatus201() throws Exception{
		
		String email = "test@tetst.com";
		User user = new User();
		user.setName("Alan Perez");
		user.setEmail(email);
		user.setIsactive(true);
		user.setPassword("Alan12");
		List<Phone> phones = new ArrayList<Phone>();
		Phone newphone = new Phone();
		newphone.setCitycode("2");
		newphone.setcountrycode("56");
		newphone.setNumber("12345678");
		phones.add(newphone);
		user.setPhones(phones);
		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/user/create")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		
		
	}
	
	@Test
	public void createUserWithHttpStatus400() throws Exception{
		
		String email = "test2@test.com";
		User user = new User();
		user.setName("Alan Perez");
		user.setEmail(email);
		user.setIsactive(true);
		user.setPassword("Alan12");
		List<Phone> phones = new ArrayList<Phone>();
		Phone newphone = new Phone();
		newphone.setCitycode("2");
		newphone.setcountrycode("56");
		newphone.setNumber("12345678");
		phones.add(newphone);
		user.setPhones(phones);
		Mockito.when(iUserService.findEmail(email)).thenReturn(user);
		MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.post("/user/create")
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		
		
	}

	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
