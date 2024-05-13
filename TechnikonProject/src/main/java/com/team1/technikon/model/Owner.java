package com.team1.technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Owner {
    @Id
    @Column(unique = true)
    private long tinNumber;
    private String firstName;
    private String lastName;
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
