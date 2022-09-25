package com.in28menutes.rest.webservices.restfulweb0services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestfulWeb0servicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWeb0servicesApplication.class, args);
    }

}
