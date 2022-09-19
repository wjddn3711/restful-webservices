package com.in28menutes.rest.webservices.restfulweb0services.helloworld;

import org.springframework.web.bind.annotation.*;

// Rest API
@RestController // marking this contorller as rest controller
public class HelloWorldController {

    // hello-world
    // Mapping Request as Get Method, and map path
//    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    // return HelloWorld Bean
    // after creating bean and return, it return json type
    // bean bag -> automatically formatted into json response
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    // Path Parameters
    // /hello-world/path-variable/{name}
    // /hello-world/path-variable/Ranga
    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(
                String.format("Hello World, %s", name));
    }
}
