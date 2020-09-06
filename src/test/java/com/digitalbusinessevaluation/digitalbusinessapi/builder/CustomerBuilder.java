package com.digitalbusinessevaluation.digitalbusinessapi.builder;

import java.math.BigDecimal;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Customer;
import com.digitalbusinessevaluation.digitalbusinessapi.entity.Customer;

public class CustomerBuilder {
	
private Customer customer;
	
	private CustomerBuilder() {}
	
	public static CustomerBuilder getOneCustomer() {
		CustomerBuilder builder = new CustomerBuilder();
		builder.customer = new Customer();
		return builder;
	}
	
	public static CustomerBuilder getOneCustomerWithId() {
		CustomerBuilder builder = new CustomerBuilder();
		builder.customer = new Customer();
		builder.customer.setId("ABC 123");
		return builder;
	}
	
	
	public Customer now() {
		return this.customer;
	}
}
