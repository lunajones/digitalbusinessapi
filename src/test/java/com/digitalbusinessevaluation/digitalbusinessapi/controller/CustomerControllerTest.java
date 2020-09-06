package com.digitalbusinessevaluation.digitalbusinessapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MvcResult;

import com.digitalbusinessevaluation.digitalbusinessapi.base.impl.BaseControllerTestImpl;

public class CustomerControllerTest extends BaseControllerTestImpl<ProductController>{

	@InjectMocks
	protected CustomerController controller;

	@BeforeEach
	public void setup() throws Exception {
		this.controller = new CustomerController();
		super.setup();

	}

	@Test
	public void shouldNotFindByIdWithUnauthorized() throws Exception {
		mockMvc = this.getMockMvc(controller);

		MvcResult mvcResult = mockMvc.perform(this.getInitialRequestUnauthenticated(BASE_URL + "/customers/{id}", 1)).andReturn();
		
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(UNAUTHORIZED.value());

	}
	
	@Test
	public void shouldNotCreateWithUnauthorized() throws Exception {
		mockMvc = this.getMockMvc(controller);

		MvcResult mvcResult = mockMvc.perform(this.postInitialRequestUnauthenticated(BASE_URL + "/customers")).andReturn();
		
		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(UNAUTHORIZED.value());

	}
}
