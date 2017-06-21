package com.apssouza.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * Oauth server configuration
 *
 * @author apssouza
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    JwtServerConfiguration jwtServerConfiguration;

    @Autowired
    AuthenticationManagerBuilder authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("todo-app")
                .authorizedGrantTypes(
                        "implicit", "refresh_token", "password", "authorization_code"
                )
                .authorities("USER_READ", "USER_WRITE")
                .scopes("write")
                .resourceIds("todo")
                .secret("123456");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtServerConfiguration.tokenStore())
                .tokenEnhancer(jwtServerConfiguration.jwtTokenEnhancer())
                .authenticationManager(
                        (Authentication authentication) -> authenticationManager
                                .getOrBuild()
                                .authenticate(authentication)
                );
    }
}
