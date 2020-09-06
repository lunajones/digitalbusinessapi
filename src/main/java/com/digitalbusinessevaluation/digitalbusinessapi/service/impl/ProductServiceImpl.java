package com.digitalbusinessevaluation.digitalbusinessapi.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Product;
import com.digitalbusinessevaluation.digitalbusinessapi.repository.ProductRepository;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.base.BaseServiceImpl;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductRepository> {
	
	public Product findByName(String name) throws DataIntegrityViolationException {
		return (Product) this.getRepository().findByName(name);
		
	}
}
