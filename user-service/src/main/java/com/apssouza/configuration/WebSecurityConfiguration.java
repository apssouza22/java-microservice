package com.apssouza.configuration;

import com.apssouza.repositories.AccountRepository;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author apssouza
 */
@Configuration
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Bean
    public AuthenticationManager    authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) -> {
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        }
                )
            .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
            .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("reader")
                .password("reader")
                .authorities("FOO_READ")
                .and()
                .withUser("writer")
                .password("writer")
                .authorities("FOO_READ", "FOO_WRITE");
    }

//    @Bean
//    protected UserDetailsService userDetailsService() {
//        return (email) -> accountRepository
//                .findByEmail(email)
//                .map(a -> new User(
//                        a.getEmail(),
//                        a.getPassword(),
//                        true, true, true, true,
//                        AuthorityUtils.createAuthorityList("USER", "write")
//                ))
//                .orElseThrow(
//                        () -> new UsernameNotFoundException("could not find the user '" + email + "'")
//                );
//    }
}
