package com.team1.technikon.securityservice.bootstrap;

import com.team1.technikon.securityservice.dto.UserInfoDto;
import com.team1.technikon.securityservice.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class InitialAdmin {

    private final UserInfoService userInfoService;

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return this::run;
    }

    private void run(String... args) {
        userInfoService.addAdmin(
                new UserInfoDto(
                        "Andreas",
                        "Chrysolouris",
                        "andreas",
                            "andreas.chrysolouris@scytalys.com",
                        "1234"
                )
        );

        userInfoService.addAdmin(
                new UserInfoDto(
                        "Ioannis",
                        "Pnevmatikos",
                        "ioannis",
                        "ioannis.pnevmatikos@scytalys.com",
                        "1234"
                )
        );

        userInfoService.addAdmin(
                new UserInfoDto(
                        "Minkyeong",
                        "Youn",
                        "minkyeong",
                        "minkyeong.youn@scytalys.com",
                        "1234"
                )
        );
    }
}
