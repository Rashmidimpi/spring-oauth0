package com.mb.Auth01.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mb.Auth01.constants.AuthConstants;
import com.mb.Auth01.entity.LoginTokenRequest;

@Component
public class BeanConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	public LoginTokenRequest loginTokenRequest()
	{
		LoginTokenRequest accessTokenRequest = new LoginTokenRequest();
		
		accessTokenRequest.setClient_id(env.getProperty(AuthConstants.AUTH0_ID));
		accessTokenRequest.setGrant_type(env.getProperty(AuthConstants.PASSWORD_LOGIN_GRANT_TYPE));
		accessTokenRequest.setAudience(env.getProperty(AuthConstants.AUTH0_AUDIENCE));
		accessTokenRequest.setScope(env.getProperty(AuthConstants.AUTH0_SCOPE));
		accessTokenRequest.setClient_secret(env.getProperty(AuthConstants.AUTH0_SECRET));
		
		return accessTokenRequest;
	}

}
