package com.oskar.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FootballCoach implements Coach{
    public FootballCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Shoot at the goal for an hour";
    }

    //define init method
    @PostConstruct
    public void postConstructFootballCoach(){
        System.out.println("Performing some logic post construct in " + getClass().getSimpleName());
    }

    //define destroy method
    @PreDestroy
    public void preDestroyFootballCoach(){
        System.out.println("Performing some logic pre destroy in " + getClass().getSimpleName());
    }
}
