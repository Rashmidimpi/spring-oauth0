package com.mb.Auth01.entity;

import java.util.Date;

public class SuccessResponse<T> {
private String message;
	
	private Integer code;
	
	private T data;
	
	private Boolean error;

	private Date timestamp;

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
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

	public SuccessResponse(String message, Integer code, T data, Boolean error, Date timestamp) {
		super();
		this.message = message;
		this.code = code;
		this.data = data;
		this.error = error;
		this.timestamp = timestamp;
	}
		
	public SuccessResponse() {
		super();
	}
	

}
