package com.mb.Auth01.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mb.Auth01.entity.ValidationErrorResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
//@Data
@JsonInclude(Include.NON_NULL)

public class ErrorResponse {

	private String message;

	private Integer code;

	private Boolean error;

	private Date timestamp;

	private List<ValidationErrorResponse> validationError;

	private Object data;

	public ErrorResponse(String message, Integer code, Boolean error, Date timestamp) {
		super();
		this.message = message;
		this.code = code;
		this.error = error;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<ValidationErrorResponse> getValidationErros() {
		return validationError;
	}

	public void setValidationErros(List<ValidationErrorResponse> validationError) {
		this.validationError = validationError;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
