package com.oskar.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Lazy //its lazy, since we are not using it it isnt constructed
public class VoleyballCoach implements Coach {
    public VoleyballCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Bounce the ball 300 times";
    }
}
