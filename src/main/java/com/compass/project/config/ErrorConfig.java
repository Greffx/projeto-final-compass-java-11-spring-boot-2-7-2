package com.compass.project.config;

import java.time.LocalDateTime;

public class ErrorConfig {

	private LocalDateTime date;
	private Integer StatusError;
	private String pathError;
	private String field;
	private String messageError;
	
	public ErrorConfig(LocalDateTime date, Integer statusError, String pathError, String messageError) {
		this.date = date;
		StatusError = statusError;
		this.pathError = pathError;
		this.messageError = messageError;
	}
	
	public ErrorConfig(LocalDateTime date, Integer statusError, String pathError, String field, String messageError) {
		this.date = date;
		StatusError = statusError;
		this.pathError = pathError;
		this.field = field;
		this.messageError = messageError;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Integer getStatusError() {
		return StatusError;
	}

	public void setStatusError(Integer statusError) {
		StatusError = statusError;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public String getPathError() {
		return pathError;
	}

	public void setPathError(String pathError) {
		this.pathError = pathError;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
}