package com.digitalbusinessevaluation.digitalbusinessapi.controller.base;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.digitalbusinessevaluation.digitalbusinessapi.entity.base.BaseEntity;
import com.digitalbusinessevaluation.digitalbusinessapi.service.base.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseController<E extends BaseEntity, S extends BaseService<E>> {

	protected static final String CLIENT_ID = "HASHIDFAKE";
	@Autowired
	private BaseService<E> service;

	@Autowired
	protected ObjectMapper objectMapper;

	@GetMapping(value = "")
	public ResponseEntity<Object> findAll(@RequestHeader("client_id") String clientId) {
		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}
		return ResponseEntity.ok().body(this.getService().findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@RequestHeader("client_id") String clientId,
			@PathVariable(value = "id") String id) {
		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}
		Optional<E> optionalEntity = this.getService().findById(id);
		if (!optionalEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		}
		return ResponseEntity.ok().body(optionalEntity.get());
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Object> create(@RequestHeader("client_id") String clientId, @RequestBody @Valid E entity, UriComponentsBuilder uriComponentsBuilder) {

		try {
			E savedEntity = this.getService().save(entity);
			UriComponents uriComponents = uriComponentsBuilder.path("/" + entity.getRouteName() + "/{id}")
					.buildAndExpand(savedEntity.getId());
			return ResponseEntity.created(uriComponents.toUri()).build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") String id, @RequestBody @Valid E entity) {
		Optional<E> optionalEntity = this.getService().findById(id);

		if (!optionalEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		}

		try {
			this.getService().save(entity);
			return ResponseEntity.ok().build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(value = "id") String id) {
		Optional<E> optionalEntity = this.getService().findById(id);

		if (!optionalEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		}
		this.getService().deleteById(id);
		return ResponseEntity.noContent().build();

	}

	@SuppressWarnings("unchecked")
	public S getService() {
		return (S) service;
	}


}
