package com.digitalbusinessevaluation.digitalbusinessapi.handler.error;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Error {
	
	private Instant timestamp;
	private int status;
	private List<ErrorDetail> errors;
	
	
	
}
