package com.in28menutes.rest.webservices.restfulweb0services.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

// Rest API
@RestController // marking this contorller as rest controller
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;


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

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale(); // 현재 스레드와 연관된 로케일, 만약 없다면 default Locale
        return messageSource.getMessage("good.morning.message", null, "default Message", locale);

        /*
        en - English = Good Morning
        ko - Korean = 좋은 아침
        fr - French = Bonjour
        nl - Dutch = Goedemorgen
         */
    }
}
