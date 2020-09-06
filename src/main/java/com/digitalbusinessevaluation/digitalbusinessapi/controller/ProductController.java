package com.digitalbusinessevaluation.digitalbusinessapi.controller;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.digitalbusinessevaluation.digitalbusinessapi.controller.base.BaseController;
import com.digitalbusinessevaluation.digitalbusinessapi.entity.Product;
import com.digitalbusinessevaluation.digitalbusinessapi.handler.error.ValidationError;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/request/manager/v1/products")
public class ProductController extends BaseController<Product, ProductServiceImpl> {

	@Override
	public ResponseEntity<Object> create(@RequestHeader("client_id") String clientId,
			@RequestBody @Valid Product entity, UriComponentsBuilder uriComponentsBuilder) {

		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}
		
		try {
			if (this.getService().findByName(entity.getName()) != null) {
				return new ResponseEntity<Object>(new ValidationError("Product name is duplicated"), new HttpHeaders(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

			Product savedEntity = this.getService().save(entity);
			UriComponents uriComponents = uriComponentsBuilder.path("/" + entity.getRouteName() + "/{id}")
					.buildAndExpand(savedEntity.getId());
			return ResponseEntity.created(uriComponents.toUri()).build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
