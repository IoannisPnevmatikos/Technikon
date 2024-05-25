package com.team1.technikon.securityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePwRequestDto {
    private String username;
    private String password;
    private String newPassword;

}