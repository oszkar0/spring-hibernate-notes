package com.oskar.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define private field for the dependency
    private Coach myCoach;

    //define a constructor for dependency injection
    @Autowired //annotation tells Spring to inject a dependency (if we have one constructor @Autowired on constructor is optional
    public DemoController(Coach theCoach){
        this.myCoach = theCoach;
    }

    //spring automatically does
    //Coach theCoach = new CricketCoach();
    //DemoController = new DemoCtroller(theCoach) <- Constructor injection
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return this.myCoach.getDailyWorkout();
    }
}
