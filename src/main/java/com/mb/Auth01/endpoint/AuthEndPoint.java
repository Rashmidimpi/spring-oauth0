package com.mb.Auth01.endpoint;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mb.Auth01.constants.AuthConstants;
import com.mb.Auth01.constants.ExceptionConstants;
import com.mb.Auth01.entity.AuthLoginResponse;
import com.mb.Auth01.entity.LoginTokenRequest;
import com.mb.Auth01.entity.SocialAuthLoginResponse;
import com.mb.Auth01.entity.SocialLoginTokenRequest;
import com.mb.Auth01.exception.CustomException;

@Component
public class AuthEndPoint {
	@Autowired
	private Environment env;

	public AuthLoginResponse loginUser(String username, String password) {
		LoginTokenRequest accessTokenRequest = new LoginTokenRequest();
		AuthLoginResponse userResponse = null;

		accessTokenRequest.setClient_id(env.getProperty(AuthConstants.AUTH0_ID));
		accessTokenRequest.setGrant_type(env.getProperty(AuthConstants.PASSWORD_LOGIN_GRANT_TYPE));
		accessTokenRequest.setAudience(env.getProperty(AuthConstants.AUTH0_AUDIENCE));
		accessTokenRequest.setScope(env.getProperty(AuthConstants.AUTH0_SCOPE));
		accessTokenRequest.setUsername(username);
		accessTokenRequest.setPassword(password);
		accessTokenRequest.setClient_secret(env.getProperty(AuthConstants.AUTH0_SECRET));

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost postReq = new HttpPost(env.getProperty(AuthConstants.AUTH0_OAUTH_TOKEN_URL));

			ObjectMapper mapper = new ObjectMapper();

			postReq.setEntity(new StringEntity(mapper.writeValueAsString(accessTokenRequest)));
			postReq.setHeader(AuthConstants.ACCEPT, ContentType.APPLICATION_JSON.toString());
			postReq.setHeader(AuthConstants.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
			CloseableHttpResponse response = httpClient.execute(postReq);

			if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
				userResponse = new ObjectMapper().readValue(
						EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8), AuthLoginResponse.class);
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.FORBIDDEN.value()) {
				throw new CustomException(env.getProperty(ExceptionConstants.WRONG_EMAIL_OR_PSWD),
						HttpStatus.FORBIDDEN.value());
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
				throw new CustomException(env.getProperty(ExceptionConstants.BAD_REQUEST),
						HttpStatus.UNAUTHORIZED.value());
			} else {
				throw new CustomException(env.getProperty(ExceptionConstants.INVALID_REQUEST),
						HttpStatus.BAD_REQUEST.value());
			}

		} catch (Exception ex) {
			throw new CustomException(env.getProperty(ExceptionConstants.INTERNAL_SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		return userResponse;
	}

	public SocialAuthLoginResponse loginUserSocial(String code) {
		SocialAuthLoginResponse res = new SocialAuthLoginResponse();

		SocialLoginTokenRequest socialLoginTokenRequest = new SocialLoginTokenRequest();

		socialLoginTokenRequest.setClient_id(env.getProperty(AuthConstants.AUTH0_ID));
		socialLoginTokenRequest.setClient_secret(env.getProperty(AuthConstants.AUTH0_SECRET));
		socialLoginTokenRequest.setGrant_type(env.getProperty(AuthConstants.ACCESS_TOKEN_GRANT_TYPE));
		socialLoginTokenRequest.setRedirect_uri(env.getProperty(AuthConstants.AUTH0_REDIRECT_URI));
		socialLoginTokenRequest.setCode(code);

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpPost postReq = new HttpPost(env.getProperty(AuthConstants.AUTH0_OAUTH_TOKEN_URL));

			ObjectMapper mapper = new ObjectMapper();

			postReq.setEntity(new StringEntity(mapper.writeValueAsString(socialLoginTokenRequest)));
			postReq.setHeader(AuthConstants.ACCEPT, ContentType.APPLICATION_JSON.toString());
			postReq.setHeader(AuthConstants.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
			CloseableHttpResponse response = httpClient.execute(postReq);
			if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
				res = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8),
						SocialAuthLoginResponse.class);
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.FORBIDDEN.value()) {
				throw new CustomException(env.getProperty(ExceptionConstants.WRONG_CODE), HttpStatus.FORBIDDEN.value());
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.UNAUTHORIZED.value()) {
				throw new CustomException(env.getProperty(ExceptionConstants.BAD_REQUEST), HttpStatus.FORBIDDEN.value());
			}
		} catch (Exception ex) {
			throw new CustomException(env.getProperty(ExceptionConstants.INTERNAL_SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

		return res;

	}
}
