package com.team1.technikon.dto;

import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Owner;
import com.team1.technikon.model.enums.TypeOfProperty;

public record PropertyDto(String propertyId,
                          String address,
                          String yearOfConstruction,
                          TypeOfProperty typeOfProperty,
                          String photo,
                          MapLocation mapLocation,
                          Owner owner) {}
