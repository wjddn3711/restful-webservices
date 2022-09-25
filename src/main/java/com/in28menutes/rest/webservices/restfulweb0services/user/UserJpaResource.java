package com.in28menutes.rest.webservices.restfulweb0services.user;

import com.in28menutes.rest.webservices.restfulweb0services.user.jpa.PostRepository;
import com.in28menutes.rest.webservices.restfulweb0services.user.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserJpaResource {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRepository;

    // GET /users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }

    // http://localhost:8080/users/

    // EntityModel
    // WebMvcLinkBuilder
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){
        User user = repository.findById(id).orElse(null);

        if (user == null){
            throw new UserNotFoundException("id : "+id);
        }
        else {
            WebMvcLinkBuilder link = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
            EntityModel<User> entityModel = EntityModel.of(user, link.withRel("all-users"));
            return entityModel;
        }
    }

    // POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = repository.save(user);
        // ServletUriComponentsBuilder.fromCurrentRequest() : /users
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // DELETE /users/{id}
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        repository.deleteById(id);
    }

    // DELETE /users/{id}
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Integer id){
        User user = repository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException("id : "+id);

        return user.getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post){
        User user = repository.findById(id).orElse(null);

        if (user == null)
            throw new UserNotFoundException("id : "+id);

        // 유저 정보를 통하여 post에 유저정보 삽입
        post.setUser(user);
        Post savedPost = postRepository.save(post);

        // 로케이션 정보는 생성된 Post 의 id 를 갖도록 함
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{id}/posts/{post_id}")
    public EntityModel<Post> retrievePostForUser(@PathVariable Integer id
                                    ,@PathVariable Integer post_id){
        User user = repository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException("id : "+id);

        // 만약 Post 가 없다면 post not found 예외를 던진다
        Post post = postRepository.findById(post_id).orElse(null);
        if (post==null)
            throw new PostNotFoundException("id : "+post_id);

        WebMvcLinkBuilder link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrievePostsForUser(id));

        EntityModel<Post> entityModel = EntityModel.of(post, link.withRel("all-posts"));

        return entityModel;
    }
}
