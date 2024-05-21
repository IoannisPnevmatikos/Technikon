package com.team1.technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends BaseModel {

    @Column(unique = true)
    private String tinNumber;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String address;
    @Column(unique = true)
    private String phone;
    private boolean isActive = true;
    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Property> properties = new ArrayList<>();

    private String role;
}
