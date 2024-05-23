package com.team1.technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;
    private String address;
    @Column(unique = true)
    private String phone;
    private boolean isActive = true;
    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Property> properties = new ArrayList<>();
    private String role;
    LocalDate registrationDate = LocalDate.now();

}
