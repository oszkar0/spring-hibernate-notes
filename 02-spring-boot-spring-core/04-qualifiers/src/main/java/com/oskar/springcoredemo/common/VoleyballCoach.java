package com.oskar.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class VoleyballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Bounce the ball 300 times";
    }
}
