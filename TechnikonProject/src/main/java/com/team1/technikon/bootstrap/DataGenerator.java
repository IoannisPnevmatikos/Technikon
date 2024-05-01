package com.team1.technikon.bootstrap;

import com.github.javafaker.Faker;
import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
@Slf4j
public class DataGenerator {
    private final OwnerService ownerService;
    private final   Faker faker = new Faker();

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return this::run;
    }

    //wdawdad
    private void run(String... args) {
        for (int i = 0; i < 5; i++) {
            ownerService.createOwner(new OwnerDto(faker.number().randomNumber(),faker.name().firstName(),faker.name().lastName(), faker.address().streetAddress(),faker.phoneNumber().toString(),faker.internet().password(), faker.internet().emailAddress(), faker.leagueOfLegends().champion()));

        }
    }
}
