package com.mb.Auth01.service;

import com.mb.Auth01.entity.AuthLoginResponse;
import com.mb.Auth01.entity.LoginModel;
import com.mb.Auth01.entity.SocialAuthLoginResponse;
import com.mb.Auth01.entity.SocialLoginModel;

public interface Auth0Service {
	
AuthLoginResponse userLogin(LoginModel loginModel);
	
	SocialAuthLoginResponse userLoginSocial(SocialLoginModel loginModel);

}
