package com.tinybeans.backend.evaluation.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tinybeans.backend.evaluation.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instatiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

}
