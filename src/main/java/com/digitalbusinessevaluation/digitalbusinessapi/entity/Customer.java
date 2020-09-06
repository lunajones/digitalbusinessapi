package com.digitalbusinessevaluation.digitalbusinessapi.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.base.BaseEntity;
import com.digitalbusinessevaluation.digitalbusinessapi.enumerator.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMER")
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class Customer extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1960160268207947975L;

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(name = "NAME", nullable = false, length = 500, unique=true)
	@NotEmpty
	@Size(min = 10, max = 500, message="Customer name is invalid. It must contain a minimum of 10 characters and a maximum of 500")
	private String name;
	
	@OneToMany(mappedBy = "customer")
	private List<Request> requests;
	
	@OneToOne
	@JoinColumn(name = "ID_ADDRESS", nullable = false)
	private Address address;

	@Column(name = "STATUS", nullable = true)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Override
	@JsonIgnore
	public String getRouteName() {
		return "customers";
	}
}
