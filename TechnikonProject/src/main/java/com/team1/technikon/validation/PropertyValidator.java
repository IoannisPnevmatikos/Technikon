package com.team1.technikon.validation;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.model.MapLocation;

import java.time.LocalDate;

public class PropertyValidator {


    public static void isValidPropertyDto(PropertyDto propertyDto) throws InvalidInputException {
        isValidE9(propertyDto.propertyId());
        isValidYearOfConstruction(propertyDto.yearOfConstruction());
        isValidAddress(propertyDto.address());
        isValidPhoto(propertyDto.photo());
        isValidMapLocation(propertyDto.mapLocation());
    }

    public static void isValidE9(String propertyId) throws InvalidInputException {
        if (!propertyId.matches("\\d{11}")) throw new InvalidInputException("Validation failed! Invalid E9.");
    }

    public static void isValidYearOfConstruction(Integer yearOfConstruction) throws InvalidInputException {
        int minRange = 1800; // Minimum range value
        int maxRange = LocalDate.now().getYear(); // Maximum range value
        if (yearOfConstruction > maxRange || yearOfConstruction < minRange)  throw new InvalidInputException("Validation failed! Invalid year of construction.");
    }

    public static void isValidAddress(String address) throws InvalidInputException {
        if (address.isEmpty()) throw new InvalidInputException("Validation failed! Address can not be empty.");
    }

    public static void isValidPhoto(String photo) throws InvalidInputException {
        if (photo.isEmpty()) throw new InvalidInputException("Validation failed! Photo url can not be empty.");
    }

    public static void isValidMapLocation(MapLocation mapLocation) throws InvalidInputException {
        if (mapLocation==null) throw new InvalidInputException("Validation failed! Coordinates can not be null.");
    }
}
