package com.team1.technikon.dto;

import com.team1.technikon.securityservice.model.UserInfo;

public record OwnerDto(String tinNumber,
                       String address,
                       String phone,
                       UserInfo userInfo) {
}
