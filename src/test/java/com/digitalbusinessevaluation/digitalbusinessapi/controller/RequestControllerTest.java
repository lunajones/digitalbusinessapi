package com.digitalbusinessevaluation.digitalbusinessapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MvcResult;

import com.digitalbusinessevaluation.digitalbusinessapi.base.impl.BaseControllerTestImpl;
import com.digitalbusinessevaluation.digitalbusinessapi.builder.CustomerBuilder;
import com.digitalbusinessevaluation.digitalbusinessapi.builder.RequestBuilder;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.CustomerServiceImpl;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.ProductServiceImpl;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.RequestServiceImpl;

public class RequestControllerTest extends BaseControllerTestImpl<RequestController> {

	@Mock
	private RequestServiceImpl service;
	
	@Mock
	private CustomerServiceImpl customerService;
	
	@Mock
	private ProductServiceImpl productService;

	@InjectMocks
	private RequestController controller;

	@BeforeEach
	public void setup() throws Exception {
		this.controller = new RequestController();
		super.setup();

	}

	@Test
	public void shouldNotFindByIdWithUnauthorized() throws Exception {
		mockMvc = this.getMockMvc(controller);

		MvcResult mvcResult = mockMvc.perform(this.getInitialRequestUnauthenticated(BASE_URL + "/requests/{id}", 1))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(UNAUTHORIZED.value());

	}

	@Test
	public void shouldNotFindById() throws Exception {
		mockMvc = this.getMockMvc(controller);

		when(this.service.findById(Mockito.anyString())).thenReturn(Optional.of(null));

		MvcResult mvcResult = mockMvc
				.perform(this.get(BASE_URL + "/requests/{id}", RequestBuilder.getOneRequestWithId().now().getId()))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(NOT_FOUND.value());

	}

	@Test
	public void shouldFindById() throws Exception {
		mockMvc = this.getMockMvc(controller);

		when(this.service.findById(Mockito.anyString()))
				.thenReturn(Optional.of(RequestBuilder.getOneRequestWithId().now()));

		MvcResult mvcResult = mockMvc
				.perform(this.get(BASE_URL + "/requests/{id}", RequestBuilder.getOneRequestWithId().now().getId()))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(OK.value());

	}

	@Test
	public void shouldNotCreateWithUnauthorized() throws Exception {
		mockMvc = this.getMockMvc(controller);

		MvcResult mvcResult = mockMvc.perform(this.postInitialRequestUnauthenticated(BASE_URL + "/requests")
				.content(this.convertToJson(RequestBuilder.getOneRequest().now()))).andReturn();
		;

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(UNAUTHORIZED.value());

	}
	
	@Test
	public void shouldNotCreateWithCustomerNotFound() throws Exception {
		mockMvc = this.getMockMvc(controller);
		when(this.customerService.findById(Mockito.anyString())).thenReturn(Optional.of(null));
		
		
		MvcResult mvcResult = mockMvc.perform(this.post(BASE_URL + "/requests")
				.content(this.convertToJson(RequestBuilder.getOneRequest().now()))).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(UNPROCESSABLE_ENTITY.value());

	}
	
	@Test
	public void shouldNotCreateWithProductNotFound() throws Exception {
		mockMvc = this.getMockMvc(controller);
		when(this.customerService.findById(Mockito.anyString())).thenReturn(Optional.of(CustomerBuilder.getOneCustomerWithId().now()));
		when(this.productService.findById(Mockito.anyString())).thenReturn(null);
		
		MvcResult mvcResult = mockMvc.perform(this.post(BASE_URL + "/requests")
				.content(this.convertToJson(RequestBuilder.getOneRequest().now()))).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(UNPROCESSABLE_ENTITY.value());

	}

}
