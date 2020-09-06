package com.digitalbusinessevaluation.digitalbusinessapi.builder;

import java.math.BigDecimal;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Customer;
import com.digitalbusinessevaluation.digitalbusinessapi.entity.Product;
import com.digitalbusinessevaluation.digitalbusinessapi.entity.Request;

public class RequestBuilder {
	
	private Request request;
	
	private RequestBuilder() {}
	
	public static RequestBuilder getOneRequest() {
		RequestBuilder builder = new RequestBuilder();
		builder.request = new Request();
		builder.request.setAmount(10);
		builder.request.setValue(new BigDecimal(200));
		builder.request.setCustomer(new Customer());
		builder.request.setProduct(new Product());
		return builder;
	}
	
	public static RequestBuilder getOneRequestWithId() {
		RequestBuilder builder = new RequestBuilder();
		builder.request = new Request();
		builder.request.setId("ABC 123");
		builder.request.setAmount(10);
		builder.request.setValue(new BigDecimal(200));
		builder.request.setCustomer(CustomerBuilder.getOneCustomerWithId().now());
		builder.request.setProduct(ProductBuilder.getOneProductWithId().now());
		return builder;
	}
	
	
	
	public Request now() {
		return this.request;
	}
}
