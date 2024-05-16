package com.team1.technikon.securityservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("USER_INFO")
public class UserInfo extends BaseAccount {
    private String role;
}