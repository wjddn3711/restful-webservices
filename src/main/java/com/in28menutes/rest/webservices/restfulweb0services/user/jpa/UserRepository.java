package com.in28menutes.rest.webservices.restfulweb0services.user.jpa;

import com.in28menutes.rest.webservices.restfulweb0services.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
