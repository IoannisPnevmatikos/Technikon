package com.team1.technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team1.technikon.securityservice.model.UserInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Owner extends BaseModel {

    @Column(unique = true)
    private String tinNumber;
    @NotBlank
    private String address;
    @Column(unique = true)
    private String phone;
    private boolean isActive;

    @OneToOne(mappedBy = "userInfo")
    UserInfo userInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Property> properties = new ArrayList<>();

}
