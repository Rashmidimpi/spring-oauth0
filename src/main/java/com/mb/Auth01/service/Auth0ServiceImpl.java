package com.mb.Auth01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mb.Auth01.constants.ExceptionConstants;
import com.mb.Auth01.endpoint.AuthEndPoint;
import com.mb.Auth01.exception.CustomException;
import com.mb.Auth01.entity.AuthLoginResponse;
import com.mb.Auth01.entity.LoginModel;
import com.mb.Auth01.entity.SocialAuthLoginResponse;
import com.mb.Auth01.entity.SocialLoginModel;

@Service
public class Auth0ServiceImpl implements Auth0Service{
	
	@Autowired
	private AuthEndPoint authEndPoint;

	@Override
	public AuthLoginResponse userLogin(LoginModel loginModel) {

		AuthLoginResponse loginResponse = null;
		try {
			loginResponse = authEndPoint.loginUser(loginModel.getUsername(), loginModel.getPassword());
		} catch (Exception ex) {
			throw new CustomException(ExceptionConstants.USER_NOT_FOUND, HttpStatus.NOT_FOUND.value());
		}
		return loginResponse;
	}

	@Override
	public SocialAuthLoginResponse userLoginSocial(SocialLoginModel loginModel) {

		SocialAuthLoginResponse loginResponse = null;
		try {
			loginResponse = authEndPoint.loginUserSocial(loginModel.getCode());
		} catch (Exception ex) {
			throw new CustomException(ExceptionConstants.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
		}

		return loginResponse;
	}

}
