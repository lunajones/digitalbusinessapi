package com.digitalbusinessevaluation.digitalbusinessapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;

import com.digitalbusinessevaluation.digitalbusinessapi.base.impl.BaseControllerTestImpl;
import com.digitalbusinessevaluation.digitalbusinessapi.builder.ProductBuilder;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.ProductServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest extends BaseControllerTestImpl<ProductController> {

	@Mock
	private ProductServiceImpl service;

	
	@InjectMocks
	private ProductController controller;
	
	@BeforeEach
	public void setup() throws Exception {
		this.controller = new ProductController();
		super.setup();

	}

	
	@Test
	public void shouldNotFindByIdWithUnauthorized() throws Exception {
		mockMvc = this.getMockMvc(controller);

		MvcResult mvcResult = mockMvc.perform(this.getInitialRequestUnauthenticated(BASE_URL + "/products/{id}", 1))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(UNAUTHORIZED.value());

	}
	
	@Test
	public void shouldNotFindById() throws Exception {
		mockMvc = this.getMockMvc(controller);

		when(this.service.findById(Mockito.anyString())).thenReturn(Optional.ofNullable(null));

		
		MvcResult mvcResult = mockMvc.perform(this.get(BASE_URL + "/products/{id}", ProductBuilder.getOneProductWithId().now().getId()))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(NOT_FOUND.value());

	}
	
	@Test
	public void shouldFindById() throws Exception {
		mockMvc = this.getMockMvc(controller);

		when(this.service.findById(Mockito.anyString())).thenReturn(Optional.of(ProductBuilder.getOneProductWithId().now()));

		
		MvcResult mvcResult = mockMvc.perform(this.get(BASE_URL + "/products/{id}", ProductBuilder.getOneProductWithId().now().getId()))
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(OK.value());

	}

	@Test
	public void shouldNotCreateWithUnauthorized() throws Exception {
		mockMvc = this.getMockMvc(controller);

		MvcResult mvcResult = mockMvc.perform(this.postInitialRequestUnauthenticated(BASE_URL + "/products")
				.content(this.convertToJson(ProductBuilder.getOneProduct().now()))).andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(UNAUTHORIZED.value());

	}

}