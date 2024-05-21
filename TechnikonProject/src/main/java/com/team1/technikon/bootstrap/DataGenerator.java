package com.team1.technikon.bootstrap;

import com.github.javafaker.Faker;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.*;
import com.team1.technikon.service.OwnerService;
import com.team1.technikon.service.PropertyService;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class DataGenerator {
    private final OwnerService ownerService;
    private final PropertyService propertyService;
    private final RepairService repairService;

    private final Faker faker = new Faker();

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return this::run;
    }

    private void run(String... args) throws OwnerFailToCreateException, OwnerNotFoundException, InvalidInputException, EntityFailToCreateException, EntityNotFoundException, UnauthorizedAccessException {
        ownerService.addAdmin(
                new SignUpDto(
                        "andreas",
                        "1234",
                        "andreas.chrysolouris@scytalys.com"
                )
        );

        ownerService.addAdmin(

                new SignUpDto(
                        "Ioannis",
                        "1234",
                        "ioannis.pnevmatikos@scytalys.com"
                )
        );
        ownerService.addAdmin(
                new SignUpDto(
                        "minkyeong",
                        "1234",
                        "minkyeong.youn@scytalys.com"
                )
        );


//        for (int i = 0; i < 3; i++) {
//            String tin = String.valueOf(faker.number().numberBetween(100000000, 999999999));
//            ownerService.createOwner(new OwnerDto(
//                    tin,
//                    faker.address().streetAddress(),
//                    faker.phoneNumber().phoneNumber().replaceAll("-", "").replaceAll("[()]", "").replace(".", "").replace(" ", "").substring(0, 10),
//                    null
//            ));
//
//            if (ownerService.getOwnerByTin(tin) != null) continue;
//            int jm = faker.number().numberBetween(1, 3);
//            for (int j = 0; j < jm; j++) {
//                long idLong = faker.number().numberBetween(10000000000L, 99999999999L);
//                String id = String.valueOf(idLong);
//                OwnerDto ownerDto = ownerService.getOwnerByTin(tin);
//                Owner owner = mapToOwner(ownerDto);
//                propertyService.createProperty(new PropertyDto(
//                                id,
//                                faker.address().streetAddress(),
//                                "" + faker.number().numberBetween(1900, 2024),
//                                TypeOfProperty.values()[faker.number().numberBetween(0, 3)],
//                                faker.leagueOfLegends().champion(),
//                                new MapLocation(0.0, 0.0),
//                                owner
//                        )
//                );
//                if (propertyService.getPropertyById(null, id) == null) continue;
//                int km = faker.number().numberBetween(1, 3);
//                for (int k = 0; k < km; k++) {
//                    repairService.createRepair(new RepairDto(
//                                    faker.date().birthday(0, 5).toInstant()
//                                            .atZone(ZoneId.systemDefault()).toLocalDate(),
//                                    faker.lorem().toString(),
//                                    TypeOfRepair.values()[faker.number().numberBetween(0, 5)],
//                                    StatusOfRepair.values()[faker.number().numberBetween(0, 3)],
//                                    new BigDecimal(faker.number().randomDouble(2, 0, 1000)),
//                                    faker.lorem().toString(),
//                                    mapToProperty(propertyService.getPropertyById(null, id))
//                            )
//                    );
//                }
//            }
//        }
    }
}
