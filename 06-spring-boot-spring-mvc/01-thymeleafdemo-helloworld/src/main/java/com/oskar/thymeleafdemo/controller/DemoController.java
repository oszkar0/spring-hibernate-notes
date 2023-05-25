package com.oskar.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DemoController {
    //create a mapping for "/hello"
    @GetMapping("/hello")
    public String sayHello(Model theModel){
        theModel.addAttribute("theDate", new java.util.Date());
        return "helloworld"; //since we have thymeleaf in Maven POM spring boot will auto look for helloworld.html in resources, we have to create thymeleaf template
    }
}
