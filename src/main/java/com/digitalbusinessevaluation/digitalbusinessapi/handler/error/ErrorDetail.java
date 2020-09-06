package com.digitalbusinessevaluation.digitalbusinessapi.handler.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetail {
	
	private String code;
	private String description;
}
