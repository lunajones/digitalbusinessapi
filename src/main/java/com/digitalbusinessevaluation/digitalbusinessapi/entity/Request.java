package com.digitalbusinessevaluation.digitalbusinessapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "REQUEST")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Request extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3587439194696405227L;

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;


	@OneToOne
	@JoinColumn(name = "ID_PRODUCT", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "ID_CUSTOMER", nullable = false)
	@JsonIgnore
	private Customer customer;
	
	@Column(name = "AMOUNT", nullable = false)
	@NonNull
	@Min(value = 1, message="Product amount is invalid. It must be a minimum of 1 and a maximum of 10")
	@Max(value = 10, message="Product amount is invalid. It must be a minimum of 1 and a maximum of 10")
	private Integer amount;
	

	@Column(name = "VALUE", nullable = false)
	@NonNull
	@Min(value = 0, message="Product value is invalid. It must be a minimum of 0")
	private BigDecimal value;
	
	@Override
	@JsonIgnore
	public String getRouteName() {
		return "requests";
	}

}
