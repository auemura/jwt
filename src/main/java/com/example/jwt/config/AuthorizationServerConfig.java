package com.example.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${jwt.client-id}")
	private String clientId;
	
	@Value("${jwt.client-secret}")
	private String clientSecret;
	
	@Value("${jwt.grant-type-pass}")
	private String grantTypePass;
	
	@Value("${jwt.authorization-code}")
	private String authorizationCode;

	@Value("${jwt.refresh-token}")
	private String refreshToken;
	
	@Value("${jwt.implicit}")
	private String implicit;
	
	@Value("${jwt.scope-read}")
	private String scopeRead;
	
	@Value("${jwt.scope-write}")
	private String scopeWrite;
	
	@Value("${jwt.trust}")
	private String trust;

	@Value("${jwt.access-token-validity}")
	private String accessTokenValidity;
	
	@Value("${jwt.refresh-token-validity}")
	private String refreshTokenValidity;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("teste");
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer
				.inMemory()
				.withClient(clientId)
				.secret(clientSecret)
				.authorizedGrantTypes(grantTypePass, authorizationCode, refreshToken, implicit )
				.scopes(scopeRead, scopeWrite, trust)
				.accessTokenValiditySeconds(Integer.parseInt(accessTokenValidity)).
				refreshTokenValiditySeconds(Integer.parseInt(refreshTokenValidity));
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())
				.authenticationManager(authenticationManager)
				.accessTokenConverter(accessTokenConverter());
	}
}