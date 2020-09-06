package com.digitalbusinessevaluation.digitalbusinessapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Customer;
import com.digitalbusinessevaluation.digitalbusinessapi.repository.CustomerRepository;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.base.BaseServiceImpl;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, CustomerRepository> {
	
	@Autowired
	private AddressServiceImpl addressService;
	
	@Override
	public Customer save(Customer customer) throws DataIntegrityViolationException {
		this.addressService.save(customer.getAddress());
		
		return super.save(customer);
	}
	
	public Customer findByName(String name) throws DataIntegrityViolationException {
		return (Customer) this.getRepository().findByName(name);
		
	}


}
