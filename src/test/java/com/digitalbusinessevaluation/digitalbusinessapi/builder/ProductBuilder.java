package com.digitalbusinessevaluation.digitalbusinessapi.builder;

import java.math.BigDecimal;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Product;

public class ProductBuilder {
	
	private Product product;
	
	private ProductBuilder() {}
	
	public static ProductBuilder getOneProduct() {
		ProductBuilder builder = new ProductBuilder();
		builder.product = new Product();
		builder.product.setName("PRODUCT NAME 1");
		builder.product.setValue(new BigDecimal(20));
		return builder;
	}
	
	public static ProductBuilder getOneProductWithId() {
		ProductBuilder builder = new ProductBuilder();
		builder.product = new Product();
		builder.product.setId("ÂBC 123");
		builder.product.setName("PRODUCT NAME 1");
		builder.product.setValue(new BigDecimal(20));
		return builder;
	}
	
	
	
	public Product now() {
		return this.product;
	}
}
