package com.digitalbusinessevaluation.digitalbusinessapi.vo;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Product;

import lombok.Data;

@Data
public class SmallProductVO {

	private String id;
	private String name;

	
	public SmallProductVO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
	}
}
