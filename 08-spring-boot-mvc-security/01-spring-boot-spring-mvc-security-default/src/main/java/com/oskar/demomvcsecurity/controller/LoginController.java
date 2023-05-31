package com.oskar.demomvcsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/customLoginPage")
    public String showMyLoginPage(){
        //return "plain-login";
        return "fancy-login";
    }

    //add a request mapping for /access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
