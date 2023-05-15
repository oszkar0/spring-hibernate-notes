package com.oskar.springbootdemo.mydemoapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//first add anotation
//@RequestMapping("/")
@RestController
//@RequestMapping("/") its a default value here
public class FunRestController {
    //expose "/" that return "hello world"
    @GetMapping("/") //means that this method will be called wvery time user comes to the main page of website for example www.mydemoapp.com/
    public String sayHello(){
        return "hello world";
    }

    //expose a new endpoint for /oskar
    @GetMapping("/oskar") //dev tools restarts app automatically
    public String getOskarString(){
        return "Oskar Oskar Oskar";
    }

    //expose new endpoint for /car

    @GetMapping("/car")
    public String getCar(){
        return "Tesla model X";
    }

}
