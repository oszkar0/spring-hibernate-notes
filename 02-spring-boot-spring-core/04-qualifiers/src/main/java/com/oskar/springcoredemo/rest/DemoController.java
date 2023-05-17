package com.oskar.springcoredemo.rest;

import com.oskar.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define private field for the dependency
    private Coach myCoach;

    //add @qualifier
    //bean name inside brackets but starting with lower case letter
    //to specify bean in dependency injection
    @Autowired
    public DemoController(@Qualifier("footballCoach") Coach theCoach){
        this.myCoach = theCoach;
    }
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return this.myCoach.getDailyWorkout();
    }
}
