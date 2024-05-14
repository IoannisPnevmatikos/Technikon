package com.team1.technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    private String firstName;
    private String lastName;
    @NotBlank
    private String address;
    @Column(unique = true)
    private String phone;
    private String password;
    @Column(unique = true)
    private String email; //Unique // NA TO DOYME KSANA
    @Column(unique = true)
    private String username; //Unique // NA TO DOYME KSANA
    private boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Property> properties = new ArrayList<>();

}
