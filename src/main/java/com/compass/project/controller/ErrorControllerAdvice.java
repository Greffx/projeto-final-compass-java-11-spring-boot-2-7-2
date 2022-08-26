package com.compass.project.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.compass.project.config.ErrorConfig;

@RestControllerAdvice
public class ErrorControllerAdvice {

	@ExceptionHandler(value = { EntityNotFoundException.class, NoSuchFieldException.class,
			EmptyResultDataAccessException.class, NoSuchElementException.class })
	public ResponseEntity<ErrorConfig> nullException(HttpServletRequest request, Exception ex) {
		ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), request.getRequestURI(),
				"URL", "Not Found: Missing or Invalid parameters");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

	@ExceptionHandler(value = { ConstraintViolationException.class, HttpMessageConversionException.class,
			MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class })
	public ResponseEntity<ErrorConfig> badRequestException(HttpServletRequest request, Exception ex) {
		ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				request.getRequestURI(), "URL", "Bad Request: Missing or Invalid parameters");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class, HttpMessageNotReadableException.class })
	public ResponseEntity<ErrorConfig> internalErrorServerException(HttpServletRequest request, Exception ex) {
		ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				request.getRequestURI(), "URL", "Internal Error: Missing or Invalid parameters");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<List<ErrorConfig>> putAndPostException(HttpServletRequest request,
			MethodArgumentNotValidException ex) {
		List<ErrorConfig> errorsList = new ArrayList<>();
		List<FieldError> fields = ex.getBindingResult().getFieldErrors();
		fields.forEach(exception -> {
			String message = exception.getDefaultMessage();
			ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
					request.getRequestURI(), exception.getField(), message);
			errorsList.add(error);
		});
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsList);
	}
}
