package com.digitalbusinessevaluation.digitalbusinessapi.controller;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.digitalbusinessevaluation.digitalbusinessapi.controller.base.BaseController;
import com.digitalbusinessevaluation.digitalbusinessapi.entity.Request;
import com.digitalbusinessevaluation.digitalbusinessapi.handler.error.ValidationError;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.CustomerServiceImpl;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.ProductServiceImpl;
import com.digitalbusinessevaluation.digitalbusinessapi.service.impl.RequestServiceImpl;
import com.digitalbusinessevaluation.digitalbusinessapi.vo.RequestVO;

@RestController
@RequestMapping("/request/manager/v1/requests")
public class RequestController extends BaseController<Request, RequestServiceImpl> {

	@Autowired
	private CustomerServiceImpl customerService;

	@Autowired
	private ProductServiceImpl productService;

	@Override
	public ResponseEntity<Object> findById(@RequestHeader("client_id") String clientId,
			@PathVariable(value = "id") String id) {
		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}
		Optional<Request> optionalEntity = this.getService().findById(id);
		if (!optionalEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
		}
		return ResponseEntity.ok().body(new RequestVO(optionalEntity.get()));
	}

	@Override
	public ResponseEntity<Object> create(@RequestHeader("client_id") String clientId,
			@RequestBody @Valid Request entity, UriComponentsBuilder uriComponentsBuilder) {

		if (!CLIENT_ID.equals(clientId)) {
			return ResponseEntity.status(UNAUTHORIZED).body("Access unauthorized");
		}

		try {
			if (!this.customerService.findById(entity.getCustomer().getId()).isPresent()) {
				return new ResponseEntity<Object>(new ValidationError("Customer does not exist"), new HttpHeaders(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

			if (!this.productService.findById(entity.getProduct().getId()).isPresent()) {
				return new ResponseEntity<Object>(new ValidationError("Product does not exist"), new HttpHeaders(),
						HttpStatus.UNPROCESSABLE_ENTITY);
			}

			Request savedEntity = this.getService().save(entity);
			UriComponents uriComponents = uriComponentsBuilder.path("/" + entity.getRouteName() + "/{id}")
					.buildAndExpand(savedEntity.getId());
			return ResponseEntity.created(uriComponents.toUri()).build();
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
