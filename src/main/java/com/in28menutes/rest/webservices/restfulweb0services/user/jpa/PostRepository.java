package com.in28menutes.rest.webservices.restfulweb0services.user.jpa;

import com.in28menutes.rest.webservices.restfulweb0services.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
