package com.team1.technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team1.technikon.securityservice.model.BaseAccount;
import com.team1.technikon.securityservice.model.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("OWNER")
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends BaseAccount {

    @Column(unique = true)
    private String tinNumber;
    @NotBlank
    private String address;
    @Column(unique = true)
    private String phone;
    private boolean isActive = true;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Property> properties = new ArrayList<>();
}
