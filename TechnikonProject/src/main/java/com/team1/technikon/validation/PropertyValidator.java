package com.team1.technikon.validation;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.InvalidInputException;

import java.time.LocalDate;

public class PropertyValidator {


    public static void isValidPropertyDto(PropertyDto propertyDto) throws InvalidInputException {
        isValidE9(propertyDto.propertyId());
        isValidYearOfConstruction(propertyDto.yearOfConstruction());
    }

    public static void isValidE9(String propertyId) throws InvalidInputException {
        if (!propertyId.matches("\\d{11}")) throw new InvalidInputException("Validation failed! Invalid E9.");
    }

    public static void isValidYearOfConstruction(String yearOfConstruction) throws InvalidInputException {
        int minRange = 1800; // Minimum range value
        int maxRange = LocalDate.now().getYear(); // Maximum range value
        String regex = String.format("\\b([1-9]%d|[1-9]\\d{2}|9[0-%d]\\d|9%d)\\b", minRange / 1000, (maxRange / 1000) % 10, (maxRange % 1000) / 100);
        if (!yearOfConstruction.matches(regex))  throw new InvalidInputException("Validation failed! Invalid year of construction.");
    }
}
