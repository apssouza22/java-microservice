
package com.apssouza.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author apssouza
 */
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    String applicationName = "users";

    // This is required for password grants, which we specify below as one of the
    // {@literal authorizedGrantTypes()}.
    @Autowired
    AuthenticationManagerBuilder authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // Workaround for https://github.com/spring-projects/spring-boot/issues/1801
        endpoints.authenticationManager(
                (Authentication authentication) -> authenticationManager
                        .getOrBuild()
                        .authenticate(authentication)
        );
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("android-" + applicationName)
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .authorities("ROLE_USER")
                .scopes("write")
                .resourceIds(applicationName)
                .secret("123456");
    }
}
// end::code[]
