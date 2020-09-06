package com.digitalbusinessevaluation.digitalbusinessapi.vo;

import java.util.ArrayList;
import java.util.List;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Customer;
import com.digitalbusinessevaluation.digitalbusinessapi.enumerator.Status;

import lombok.Data;

@Data
public class CustomerVO {

	private String id;
	private String name;
	private List<RequestVO> requests;
	private AddressVO address;
	private Status status;
	
	
	public CustomerVO(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.requests = new ArrayList<RequestVO>();
		customer.getRequests().stream().forEach(request -> this.requests.add(new RequestVO(request)));
		this.address = new AddressVO(customer.getAddress());
		this.status = customer.getStatus();
	}
}
