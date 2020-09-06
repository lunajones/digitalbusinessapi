package com.digitalbusinessevaluation.digitalbusinessapi.handler.error;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class DigitalBusinessAPIExceptionHandler {

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<ValidationError> validationErrors = new ArrayList<ValidationError>();

		ex.getBindingResult().getFieldErrors()
				.forEach(fieldError -> validationErrors.add(new ValidationError(fieldError.getDefaultMessage())));

		return new ResponseEntity<Object>(validationErrors, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleInvalidEnumOption(HttpMessageNotReadableException ex) {
		String specificCause = ex.getMostSpecificCause().toString();
		ValidationError validationError = new ValidationError(
				specificCause.substring(specificCause.indexOf("enumerator.") + 11, specificCause.indexOf("` from "))
						+ " does not exist");
		return new ResponseEntity<Object>(validationError, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Object> handleNoHandlerFound(NoHandlerFoundException ex, WebRequest request) {
		return new ResponseEntity<Object>("Resource not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
		return new ResponseEntity<Object>(
				new Error(Instant.now(), HttpStatus.BAD_REQUEST.value(),
						Arrays.asList(new ErrorDetail(ex.getLocalizedMessage(), ex.getMessage()))),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}