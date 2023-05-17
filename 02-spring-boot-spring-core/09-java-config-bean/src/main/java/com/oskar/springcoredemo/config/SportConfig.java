package com.oskar.springcoredemo.config;

import com.oskar.springcoredemo.common.Coach;
import com.oskar.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    @Bean //we can make @Bean("aquatic") so then in @qualifier it will be calles by @Qualifier("Aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
