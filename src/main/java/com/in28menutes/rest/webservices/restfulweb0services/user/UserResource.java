package com.in28menutes.rest.webservices.restfulweb0services.user;

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
public class UserResource {
    @Autowired
    private UserDaoService service;


    // GET /users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // http://localhost:8080/users/

    // EntityModel
    // WebMvcLinkBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){
        User user = service.findOne(id);

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
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        // ServletUriComponentsBuilder.fromCurrentRequest() : /users
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // DELETE /users/{id}
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.delete(id);
    }
}
