package org.client.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OAuth2ClientConfig {
	
	@Autowired private ClientTokenServices clientTokenService;
	@Autowired private OAuth2ClientContext oauth2ClientContext;
	
	/*
	 * Oauth2 서버 설정
	 */
	@Bean
	public OAuth2ProtectedResourceDetails authorizationCode() {
		
		AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
		
		resourceDetails.setId("daniel_client");
		resourceDetails.setTokenName("oauth_token");
		resourceDetails.setClientId("4c73df0f-a991-46e2-ba3e-94ff55c3c487");
		resourceDetails.setClientSecret("4901799c-843a-49dd-81bb-52f368b6dba4");
		resourceDetails.setAccessTokenUri("http://localhost:8080/oauth/token");
		resourceDetails.setUserAuthorizationUri("http://localhost:8080/oauth/authorize");
		resourceDetails.setScope(Arrays.asList("read", "write"));
		resourceDetails.setPreEstablishedRedirectUri("http://localhost:9000/callback");
		resourceDetails.setGrantType("authorization_code,implicit,password,client_credentials,refresh_token");
		resourceDetails.setUseCurrentUri(false);
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		
		return resourceDetails;
		
		//return new AuthorizationCodeResourceDetails();
	}
	
	@Bean
	public OAuth2RestTemplate oauth2RestTemplate() {
		OAuth2ProtectedResourceDetails resourceDetails = authorizationCode();
		
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, oauth2ClientContext);
		
		AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
		provider.setClientTokenServices(clientTokenService);

		restTemplate.setAccessTokenProvider(provider);

		return restTemplate;
	}
	
}
