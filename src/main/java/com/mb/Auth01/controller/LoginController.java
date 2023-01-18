package com.mb.Auth01.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mb.Auth01.entity.SocialAuthLoginResponse;
import com.mb.Auth01.entity.SocialLoginModel;
import com.mb.Auth01.constants.ResponseConstants;
import com.mb.Auth01.entity.AuthLoginResponse;
import com.mb.Auth01.entity.LoginModel;
import com.mb.Auth01.entity.SuccessResponse;
import com.mb.Auth01.service.Auth0Service;
import com.mb.Auth01.util.ResponseMaker;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private Environment env;

	@Autowired
	private ResponseMaker responseMaker;
	
	@Autowired
	private Auth0Service auth0Service;
	
	@PostMapping
	public ResponseEntity<SuccessResponse<AuthLoginResponse>> loginUser(@RequestBody @Valid LoginModel loginModel) {
		AuthLoginResponse loginResponse = auth0Service.userLogin(loginModel);

		return responseMaker.successResponse(env.getProperty(ResponseConstants.SUCCESS), loginResponse);
	}
	
	@PostMapping(value = "/social")
	public ResponseEntity<SuccessResponse<SocialAuthLoginResponse>> loginUserSocial(
			@RequestBody @Valid SocialLoginModel loginModel) {
		SocialAuthLoginResponse loginResponse = auth0Service.userLoginSocial(loginModel);

		return responseMaker.successResponse(env.getProperty(ResponseConstants.SUCCESS), loginResponse);
	}



}
