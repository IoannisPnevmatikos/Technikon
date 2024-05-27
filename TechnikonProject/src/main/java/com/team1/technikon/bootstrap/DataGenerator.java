package com.team1.technikon.bootstrap;

import com.github.javafaker.Faker;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.*;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Owner;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfProperty;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.repository.RepairRepository;
import com.team1.technikon.service.AdminOwnerService;
import com.team1.technikon.validation.OwnerValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.ZoneId;

import static com.team1.technikon.validation.OwnerValidator.isValidSignUpDto;
import static com.team1.technikon.validation.OwnerValidator.isValidUsername;

@AllArgsConstructor
@Configuration
@Slf4j
public class DataGenerator {
    private final AdminOwnerService adminOwnerService;
    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;
    private final RepairRepository repairRepository;
    private PasswordEncoder encoder;
    private static final Logger logger = LoggerFactory.getLogger(OwnerValidator.class);

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
                        "pariss",
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

        for (int i = 0; i < 10; i++) {

            Owner owner = new Owner();
            owner.setTinNumber(String.valueOf(faker.number().numberBetween(100000000, 999999999)));
            String firstName = faker.name().firstName();
            owner.setFirstName(firstName.substring(0, 1).toUpperCase()+firstName.substring(1));
           String lastName = faker.name().lastName();
            owner.setFirstName(lastName.substring(0, 1).toUpperCase()+lastName.substring(1));
            owner.setUsername(faker.name().username().toLowerCase());
            owner.setPassword(encoder.encode("1234"));
            owner.setAddress(faker.address().streetAddress());
            owner.setPhone(String.valueOf(faker.number().numberBetween(6900000000L, 7000000000L)));
            owner.setRole("USER");
            owner.setActive(faker.bool().bool());
            owner.setRegistrationDate(faker.date().birthday(0, 5).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            ownerRepository.save(owner);

            for (int j = 0; j < 5; j++) {

                Property property = new Property();

                property.setPropertyId(String.valueOf(faker.number().numberBetween(10000000000L, 99999999999L)));
                property.setAddress(faker.address().streetAddress());
                property.setYearOfConstruction(faker.number().numberBetween(1800, 2024));
                property.setTypeOfProperty(TypeOfProperty.values()[faker.number().numberBetween(0, 3)]);
                property.setPhoto(faker.internet().image());
                property.setMapLocation(new MapLocation(
                        faker.number().randomDouble(2, 0, 100),
                        faker.number().randomDouble(2, 0, 100)
                ));
                property.setOwner(owner);
                property.setActive(faker.bool().bool());
                property.setRegistrationDate(faker.date().birthday(0, 5).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                propertyRepository.save(property);

                for (int k = 0; k < 5; k++) {

                    Repair repair = new Repair();

                    repair.setLocalDate(faker.date().birthday(0, 5).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    repair.setShortDescription(faker.lorem().toString());
                    repair.setTypeOfRepair(TypeOfRepair.values()[faker.number().numberBetween(0, 5)]);
                    repair.setStatusOfRepair(StatusOfRepair.values()[faker.number().numberBetween(0, 4)]);
                    repair.setCost(new BigDecimal(faker.number().randomDouble(2, 0, 1000)));
                    repair.setDescriptionText(faker.lorem().toString());
                    repair.setProperty(property);
                    repairRepository.save(repair);
                }
            }
        }
    }
}

