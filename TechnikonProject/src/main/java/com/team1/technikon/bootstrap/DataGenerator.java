package com.team1.technikon.bootstrap;

import com.github.javafaker.Faker;
import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfProperty;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.service.OwnerService;
import com.team1.technikon.service.PropertyService;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.ZoneId;

@AllArgsConstructor
@Configuration
public class DataGenerator {
    private final OwnerService ownerService;
    private final PropertyService propertyService;
    private final RepairService repairService;
    private final   Faker faker = new Faker();

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return this::run;
    }

    private void run(String... args) {
        for (int i = 0; i < 3; i++) {
            long tin = faker.number().numberBetween(100000000,999999999);
            ownerService.createOwner(new OwnerDto(
                    tin,
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.address().streetAddress(),
                    faker.phoneNumber().toString(),
                    faker.internet().password(),
                    faker.internet().emailAddress(),
                    faker.leagueOfLegends().champion()
                    )
            );
            if (ownerService.getOwnerByTin(tin)==null) continue;
            int jm = faker.number().numberBetween(1,3);
            for (int j = 0; j < jm; j++) {
                long id = faker.number().numberBetween(10000000000L,99999999999L);
                propertyService.createProperty(new PropertyDto(
                    id,
                    faker.address().streetAddress(),
                    ""+faker.number().numberBetween(1900,2024),
                    TypeOfProperty.values()[faker.number().numberBetween(0,3)],
                    faker.leagueOfLegends().champion(),
                    new MapLocation(0.0,0.0),
                    ownerService.getOwnerByTin(tin)
                    )
                );
                if (propertyService.getPropertyById(id)==null) continue;
                int km = faker.number().numberBetween(1,3);
                for (int k = 0; k < km; k++) {
                    repairService.createRepair(new RepairDto(
                            faker.date().birthday(0,5).toInstant()
                                    .atZone(ZoneId.systemDefault()).toLocalDateTime(),
                            faker.lorem().toString(),
                            TypeOfRepair.values()[faker.number().numberBetween(0,5)],
                            StatusOfRepair.values()[faker.number().numberBetween(0,3)],
                            new BigDecimal(faker.number().randomDouble(2, 0, 1000)),
                            faker.lorem().toString(),
                            ownerService.getOwnerByTin(tin),
                            propertyService.getPropertyById(id)
                            )
                    );
                }
            }
        }
    }
}
