package com.team1.technikon.securityservice.bootstrap;

import com.team1.technikon.model.Owner;
import com.team1.technikon.securityservice.dto.UserInfoDto;
import com.team1.technikon.securityservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class InitialAdmin {

    private final UserService userService;

    @Bean
    public CommandLineRunner myCommandLineRunner1() {
        return this::run1;
    }

    private void run1(String... args) {
        userService.addAdmin(
                new UserInfoDto(
                        "Andreas",
                        "Chrysolouris",
                        "andreas",
                            "andreas.chrysolouris@scytalys.com",
                        "1234"
                )
        );

        userService.addAdmin(
                new UserInfoDto(
                        "Ioannis",
                        "Pnevmatikos",
                        "ioannis",
                        "ioannis.pnevmatikos@scytalys.com",
                        "1234"
                )
        );

        UserInfoDto userInfoDto = new UserInfoDto(
                "Minkyeong",
                "Youn",
                "minkyeong",
                "minkyeong.youn@scytalys.com",
                "1234"
        );

        userService.addAdmin(
        userInfoDto
        );
//        Owner owner = new Owner();
//        owner.setPhone("1234567890");
//        owner.setAddress("sdfasdfasdf");
//        owner.setTinNumber("123465789");

//       userService.findUserByUsername("minkyeong").get().setOwner(owner);

    }
}
