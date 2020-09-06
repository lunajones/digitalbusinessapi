package com.digitalbusinessevaluation.digitalbusinessapi.vo;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.Address;
import com.digitalbusinessevaluation.digitalbusinessapi.enumerator.Province;

import lombok.Data;

@Data
public class AddressVO {
	
	private String id;
	private String street;
	private String neighborhood;
	private String city;
	private Province province;
	
	public AddressVO(Address address) {
		this.id = address.getId();
		this.street = address.getStreet();
		this.neighborhood = address.getNeighborhood();
		this.city = address.getCity();
		this.province = address.getProvince();
	}

}
