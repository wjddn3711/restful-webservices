package com.in28menutes.rest.webservices.restfulweb0services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        //ensure that all request authenticated
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        //for restapi, enable basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // CSRF -> POST, Put
        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
