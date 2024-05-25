package com.team1.technikon.bootstrap;

import com.github.javafaker.Faker;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.*;
import com.team1.technikon.model.Owner;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.service.AdminOwnerService;
import com.team1.technikon.service.OwnerService;
import com.team1.technikon.service.PropertyService;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
public class DataGenerator {
    private final OwnerService ownerService;
    private final AdminOwnerService adminOwnerService;
    private final PropertyService propertyService;
    private final RepairService repairService;
    private final OwnerRepository ownerRepository;
    private PasswordEncoder encoder;


    private final Faker faker = new Faker();

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return this::run;
    }

    private void run(String... args) throws OwnerFailToCreateException, OwnerNotFoundException, InvalidInputException, EntityFailToCreateException, EntityNotFoundException, UnauthorizedAccessException {
        adminOwnerService.addAdmin(
                new SignUpDto(
                        "andreas",
                        "1234",
                        "andreas.chrysolouris@scytalys.com"
                )
        );

        adminOwnerService.addAdmin(

                new SignUpDto(
                        "Ioannis",
                        "1234",
                        "ioannis.pnevmatikos@scytalys.com"
                )
        );
        adminOwnerService.addAdmin(
                new SignUpDto(
                        "minkyeong",
                        "1234",
                        "minkyeong.youn@scytalys.com"
                )
        );
        adminOwnerService.addAdmin(
                new SignUpDto(
                        "paris",
                        "1234",
                        "paris.migklis@scytalys.com"
                )
        );
        adminOwnerService.addAdmin(
                new SignUpDto(
                        "gavriil",
                        "1234",
                        "gavriil.theodoridis@scytalys.com"
                )
        );

        Owner owner1 = new Owner();
        Owner owner2 = new Owner();
        Owner owner3 = new Owner();
        Owner owner4 = new Owner();
        Owner owner5 = new Owner();


        owner1.setTinNumber("000000001");
        owner1.setFirstName("Owner");
        owner1.setLastName("Test");
        owner1.setUsername("user1");
        owner1.setPassword(encoder.encode("1234"));
        owner1.setEmail("user1@mail.com");
        owner1.setAddress("Omirou 6");
        owner1.setPhone("6969696901");
        owner1.setRole("USER");
        ownerRepository.save(owner1);

        owner2.setTinNumber("000000002");
        owner2.setFirstName("Owner");
        owner2.setLastName("Test");
        owner2.setUsername("user2");
        owner2.setPassword(encoder.encode("1234"));
        owner2.setEmail("user2@mail.com");
        owner2.setAddress("Omirou 6");
        owner2.setPhone("6969696902");
        owner2.setRole("USER");
        ownerRepository.save(owner2);

        owner3.setTinNumber("000000003");
        owner3.setFirstName("Owner");
        owner3.setLastName("Test");
        owner3.setUsername("user3");
        owner3.setPassword(encoder.encode("1234"));
        owner3.setEmail("user3@mail.com");
        owner3.setAddress("Omirou 6");
        owner3.setPhone("6969696903");
        owner3.setRole("USER");
        ownerRepository.save(owner3);

        owner4.setTinNumber("000000004");
        owner4.setFirstName("Owner");
        owner4.setLastName("Test");
        owner4.setUsername("user4");
        owner4.setPassword(encoder.encode("1234"));
        owner4.setEmail("user4@mail.com");
        owner4.setAddress("Omirou 6");
        owner4.setPhone("6969696904");
        owner4.setRole("USER");
        ownerRepository.save(owner4);

        owner5.setTinNumber("000000005");
        owner5.setFirstName("Owner");
        owner5.setLastName("Test");
        owner5.setUsername("user5");
        owner5.setPassword(encoder.encode("1234"));
        owner5.setEmail("user5@mail.com");
        owner5.setAddress("Omirou 6");
        owner5.setPhone("6969696905");
        owner5.setRole("USER");
        ownerRepository.save(owner5);


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
