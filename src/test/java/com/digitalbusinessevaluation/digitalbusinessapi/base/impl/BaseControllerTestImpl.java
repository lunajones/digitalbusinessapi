package com.digitalbusinessevaluation.digitalbusinessapi.base.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

public class BaseControllerTestImpl<C> {

	private static final String CLIENT_ID = "HASHIDFAKE";

	private static final String WRONG_CLIENT_ID = "HASHIDFAK";

	protected static final String BASE_URL = "http://localhost:8080/request/manager/v1";

	@Autowired
	protected MockMvc mockMvc;

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	public void tearDown() {

	}

	public MockMvc getMockMvc(Object controller) {
		return MockMvcBuilders.standaloneSetup(controller).build();
	}

	public MockHttpServletRequestBuilder getInitialRequestUnauthenticated(String uri) {
		return MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE);
	}

	public MockHttpServletRequestBuilder get(String uri) {
		return MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", CLIENT_ID);
	}

	public MockHttpServletRequestBuilder getInitialRequestUnauthenticated(String uri, Object... urlVariables) {
		return MockMvcRequestBuilders.get(uri, urlVariables).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", WRONG_CLIENT_ID);
	}

	public MockHttpServletRequestBuilder get(String uri, Object... urlVariables) {
		return MockMvcRequestBuilders.get(uri, urlVariables).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", CLIENT_ID);
	}

	public MockHttpServletRequestBuilder postInitialRequestUnauthenticated(String uri) {
		return MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", WRONG_CLIENT_ID);
	}

	public MockHttpServletRequestBuilder post(String uri) {
		return MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", CLIENT_ID);
	}

	public MockHttpServletRequestBuilder post(String uri, Object... urlVariables) {
		return MockMvcRequestBuilders.post(uri, urlVariables).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", CLIENT_ID);
	}

	public MockHttpServletRequestBuilder putInitialRequestUnauthenticated(String uri) {
		return MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", WRONG_CLIENT_ID);
	}

	public MockHttpServletRequestBuilder putInitialRequestUnauthenticated(String uri, Object... urlVariables) {
		return MockMvcRequestBuilders.put(uri, urlVariables).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", WRONG_CLIENT_ID);
	}

	public MockHttpServletRequestBuilder put(String uri) {
		return MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", CLIENT_ID);
	}

	public MockHttpServletRequestBuilder put(String uri, Object... urlVariables) {
		return MockMvcRequestBuilders.put(uri, urlVariables).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", CLIENT_ID);
	}

	public MockHttpServletRequestBuilder delete(String uri, Object... urlVariables) {
		return MockMvcRequestBuilders.delete(uri, urlVariables).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", CLIENT_ID);
	}

	public MockHttpServletRequestBuilder deleteInitialRequestUnauthenticated(String uri, Object... urlVariables) {
		return MockMvcRequestBuilders.delete(uri, urlVariables).contentType(MediaType.APPLICATION_JSON_VALUE)
				.session(new MockHttpSession()).header("client_id", WRONG_CLIENT_ID);
	}

	public String convertToJson(Object value) {
		return new Gson().toJson(value);
	}

}
