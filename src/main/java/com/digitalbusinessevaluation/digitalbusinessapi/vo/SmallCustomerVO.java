package com.digitalbusinessevaluation.digitalbusinessapi.vo;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Customer;

import lombok.Data;

@Data
public class SmallCustomerVO {

	private String id;
	private String name;
	
	public SmallCustomerVO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
	}
}
