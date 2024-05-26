package com.team1.technikon.dto;

import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.enums.TypeOfProperty;

import java.time.LocalDate;

public record PropertyDto(String propertyId,
                          String address,
                          Integer yearOfConstruction,
                          TypeOfProperty typeOfProperty,
                          String photo,
                          MapLocation mapLocation,
                          LocalDate registrationDate,
                          boolean isActive,
                          String ownerTin
) {
}
