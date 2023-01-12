package com.mb.Auth01.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class SocialLoginModel {
	@Valid
	@NotBlank
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
