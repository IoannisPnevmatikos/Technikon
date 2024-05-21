package com.team1.technikon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team1.technikon.model.enums.TypeOfProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Property extends BaseModel {
    @Column(unique = true)
    private String propertyId;
    private String address;
    private String yearOfConstruction; // CHEEEECK!!!!!!
    private TypeOfProperty typeOfProperty;
    private String photo;
    private MapLocation mapLocation;
    private boolean isActive = true;

    @ManyToOne
    private Owner owner;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private List<Repair> repairs = new ArrayList<>();
}
