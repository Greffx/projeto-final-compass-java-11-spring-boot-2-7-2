package com.compass.project.config;

import java.time.LocalDateTime;

public class ErrorConfig {

	private LocalDateTime date;
	private Integer StatusError;
	private String messageError;
	private String pathError;

	public ErrorConfig(LocalDateTime date, Integer statusError, String messageError, String pathError) {
		super();
		this.date = date;
		StatusError = statusError;
		this.messageError = messageError;
		this.pathError = pathError;
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

}
