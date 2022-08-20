package com.compass.project.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.compass.project.config.ErrorConfig;

@RestControllerAdvice
public class ErrorControllerAdvice {

	@ExceptionHandler(value = { EntityNotFoundException.class, NoSuchFieldException.class,
			EmptyResultDataAccessException.class, NoSuchElementException.class })
	public ResponseEntity<ErrorConfig> notFoundException(HttpServletRequest request) {
		ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found",
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class, HttpMessageConversionException.class })
	public ResponseEntity<ErrorConfig> badRequestException(HttpServletRequest request) {
		ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request",
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorConfig> internalErrorServerException(HttpServletRequest request) {
		ErrorConfig error = new ErrorConfig(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error", request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
