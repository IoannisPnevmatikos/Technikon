package com.team1.technikon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class TechnikonApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnikonApplication.class, args);
    }

}
