package com.compass.project.controller;

import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.compass.project.config.ErrorConfig;

@RestControllerAdvice
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { EntityNotFoundException.class, NoSuchFieldException.class })
	public ResponseEntity<ErrorConfig> notFoundException(HttpServletRequest request) {
		ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not found",
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<ErrorConfig> badRequestException(HttpServletRequest request) {
		ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request",
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler()
	public ResponseEntity<Object> internalErrorServerException(RuntimeException ex, WebRequest request) {
		String error = "Error 505 \nInternal Server Error";
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
