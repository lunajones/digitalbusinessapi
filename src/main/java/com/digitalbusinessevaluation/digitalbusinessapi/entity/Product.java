package com.digitalbusinessevaluation.digitalbusinessapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Product extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -334539982749005978L;

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;


	@Column(name = "NAME", nullable = false, length = 500)
	@NotEmpty
	@Size(max = 500, message="Product name is invalid. It must contain a maximum of 500 characters")
	private String name;

	@Column(name = "VALUE", nullable = false)
	@NonNull
	@Min(value = 0, message = "Product value is invalid. It must be a minimum of 0")
	private BigDecimal value;
	
	@Override
	@JsonIgnore
	public String getRouteName() {
		return "products";
	}

}
