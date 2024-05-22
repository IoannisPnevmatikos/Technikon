package com.team1.technikon.dto;

import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.enums.TypeOfProperty;

public record PropertyDto(String propertyId,
                          String address,
                          Integer yearOfConstruction,
                          TypeOfProperty typeOfProperty,
                          String photo,
                          MapLocation mapLocation) {
}
