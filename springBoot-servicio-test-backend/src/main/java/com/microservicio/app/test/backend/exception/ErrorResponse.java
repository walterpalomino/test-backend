package com.microservicio.app.test.backend.exception;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

	private int status;
	private String message;
	private Date timestamp;
	private List<String> errors;

	ErrorResponse(String message) {
		this.message = message;
	}

}
