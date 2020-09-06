package com.digitalbusinessevaluation.digitalbusinessapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.base.BaseEntity;
import com.digitalbusinessevaluation.digitalbusinessapi.enumerator.Province;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ADDRESS")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Address extends BaseEntity implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 6425977014812446983L;

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;


	@Column(name = "STREET", nullable = false, length = 500)
	@NotEmpty
	@Size(min = 1, max = 500)
	private String street;

	@Column(name = "NEIGHBORHOOD", nullable = false, length = 250)
	@NotEmpty
	@Size(min = 1, max = 250)
	private String neighborhood;
	
	@Column(name = "CITY", nullable = false, length = 250)
	@NotEmpty
	@Size(min = 1, max = 250)
	private String city;
	
	@Column(name = "PROVINCE", nullable = true)
	@Enumerated(EnumType.STRING)
	private Province province;

	@Override
	@JsonIgnore
	public String getRouteName() {
		return "addresses";
	}

}
