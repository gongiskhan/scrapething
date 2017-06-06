package org.constantgatherer.config;

import org.constantgatherer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

/**
 * Created by ggomes on 28/05/14.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public DigestAuthenticationFilter authenticationFilter() throws Exception{
        DigestAuthenticationFilter authenticationFilter = new DigestAuthenticationFilter();
        authenticationFilter.setUserDetailsService(userService);
        authenticationFilter.setAuthenticationEntryPoint(authenticationEntryPoint());
        return authenticationFilter;
    }

    @Bean
    public DigestAuthenticationEntryPoint authenticationEntryPoint(){
        DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
        digestAuthenticationEntryPoint.setRealmName("Gatherers");
        digestAuthenticationEntryPoint.setKey("acegi");
        return digestAuthenticationEntryPoint;
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        /*
        http.csrf().disable()
        .userDetailsService(userService)
        .authorizeRequests().antMatchers("/api-docs", "/apidocs/images/*", "/apidocs/css/*", "/apidocs/lib/*", "/api-docs/*", "/apidocs/*", "/apidoc*").permitAll()
        .anyRequest().authenticated()
        .and().addFilter(authenticationFilter());
        */
    }
}