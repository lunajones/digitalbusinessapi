package com.digitalbusinessevaluation.digitalbusinessapi.vo;

import java.math.BigDecimal;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Request;

import lombok.Data;

@Data
public class RequestVO {
	private String id;
	private SmallProductVO product;
	private SmallCustomerVO customer;
	private Integer amount;
	private BigDecimal value;
	
	public RequestVO(Request request) {
		
		this.id = request.getId();
		this.product = new SmallProductVO(request.getProduct());
		this.customer = new SmallCustomerVO(request.getCustomer());
		this.amount = request.getAmount();
		this.value = request.getValue();
		
	}
}
