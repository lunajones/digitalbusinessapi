package com.digitalbusinessevaluation.digitalbusinessapi.controller;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.digitalbusinessevaluation.digitalbusinessapi.controller.base.BaseController;
import com.digitalbusinessevaluation.digitalbusinessapi.entity.Customer;
import com.digitalbusinessevaluation.digitalbusinessapi.handler.error.ValidationError;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.CustomerServiceImpl;
import com.digitalbusinessevaluation.digitalbusinessapi.vo.CustomerVO;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/request/manager/v1/customers")
public class CustomerController extends BaseController<Customer, CustomerServiceImpl> {

	@Override
	public ResponseEntity<Object> findAll(@RequestHeader("client_id") String clientId) {
		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}

		List<CustomerVO> customers = new ArrayList<CustomerVO>();
		this.getService().findAll().stream().forEach(customer -> customers.add(new CustomerVO(customer)));
		return ResponseEntity.ok().body(customers);
	}

	@Override
	public ResponseEntity<Object> findById(@RequestHeader("client_id") String clientId,
			@PathVariable(value = "id") String id) {
		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}
		Optional<Customer> optionalEntity = this.getService().findById(id);
		if (!optionalEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		}
		return ResponseEntity.ok().body(new CustomerVO(optionalEntity.get()));

	}

	@Override
	public ResponseEntity<Object> create(@RequestHeader("client_id") String clientId,
			@RequestBody @Valid Customer entity, UriComponentsBuilder uriComponentsBuilder) {
		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}

		try {
			if (this.getService().findByName(entity.getName()) != null) {
				return new ResponseEntity<Object>(new ValidationError("Customer name is duplicated"), new HttpHeaders(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

			Customer savedEntity = this.getService().save(entity);
			UriComponents uriComponents = uriComponentsBuilder.path("/" + entity.getRouteName() + "/{id}")
					.buildAndExpand(savedEntity.getId());
			return ResponseEntity.created(uriComponents.toUri()).build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<Object> patch(@RequestHeader("client_id") String clientId,
			@PathVariable(value = "id") String id, @RequestBody String body) {
		Optional<Customer> optionalEntity = this.getService().findById(id);

		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}

		if (!optionalEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		}

		try {

			Customer entityToUpdate = objectMapper.readerForUpdating(optionalEntity.get()).readValue(body);

			this.getService().save(entityToUpdate);

			return ResponseEntity.ok().body(entityToUpdate);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().build();
		} catch (JsonProcessingException e) {
			return new ResponseEntity<Object>(new ValidationError("Status does not exist"), new HttpHeaders(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}
}
