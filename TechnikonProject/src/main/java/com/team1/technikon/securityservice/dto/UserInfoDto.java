package com.team1.technikon.securityservice.dto;

import jakarta.persistence.Column;

public record UserInfoDto(
        String firstName,
        String lastName,
        String username,
        String email,
        String password
) {
}
