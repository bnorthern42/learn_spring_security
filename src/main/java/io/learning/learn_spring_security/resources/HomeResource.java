package io.learning.learn_spring_security.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home(){
        return ("<h1>WELCOME HOME!</h1>");
    }


    @GetMapping("/user")
    public String user(){
        return ("<h1>WELCOME User!</h1>");
    }


    @GetMapping("/admin")
    public String admin(){
        return ("<h1>WELCOME Admin!</h1>");
    }

}
