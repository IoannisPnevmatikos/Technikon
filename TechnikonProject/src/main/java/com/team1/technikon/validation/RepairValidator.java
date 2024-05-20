package com.team1.technikon.validation;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;

public class RepairValidator {

    public static void validateCreateRepair(RepairDto repairDto) throws InvalidInputException {
        validateLocalDateAtCreate(repairDto.localDate());
        validateShortDescription(repairDto.shortDescription());
        validateTypeOfRepair(repairDto.typeOfRepair());
        validateStatusOfRepair(repairDto.statusOfRepair());
        validateCost(repairDto.cost());
        validateDescriptionText(repairDto.descriptionText());
    }

    public static void validateTinNumber(String tinNumber) throws InvalidInputException {
        if (tinNumber == null || tinNumber.isEmpty() || !tinNumber.matches("\\d{11}")) {
            throw new InvalidInputException("TIN number is in wrong form.");
        }
    }

    public static void validateLocalDate(LocalDate localDate) throws InvalidInputException {
        if (localDate == null) {
            throw new InvalidInputException("LocalDate cannot be null");
        }
    }

    public static void validateLocalDateAtCreate(LocalDate localDate) throws InvalidInputException {
        if (localDate == null) {
            throw new InvalidInputException("LocalDate cannot be null");
        }

        if (localDate.isBefore(LocalDate.now())) {
            throw new InvalidInputException("LocalDate cannot be in the past");
        }

        LocalDate latestAllowedDate = LocalDate.of(2100, 12, 31);

        if (localDate.isAfter(latestAllowedDate)) {
            throw new InvalidInputException("LocalDate must be before " + latestAllowedDate);
        }

        if (!localDate.isLeapYear() && localDate.getMonthValue() == 2 && localDate.getDayOfMonth() == 29) {
            throw new InvalidInputException("Invalid date for non-leap year");
        }

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new InvalidInputException("Date cannot fall on a weekend");
        }
    }

    public static void validateDateRange(LocalDate startingDate, LocalDate endingDate) throws InvalidInputException {
        if (startingDate == null || endingDate == null || startingDate.isAfter(endingDate)) {
            throw new InvalidInputException("Invalid date range.");
        }
    }

    public static void validateShortDescription(String shortDescription) throws InvalidInputException {
        if (shortDescription == null || shortDescription.length() < 3 || shortDescription.length() > 255) {
            throw new InvalidInputException("Short description must be between 3 and 255 characters");
        }

        if (shortDescription.trim().isEmpty()) {
            throw new InvalidInputException("Short description cannot be empty or consist only of whitespace");
        }

        if (!shortDescription.matches("^[a-zA-Z0-9\\s]+$")) {
            throw new InvalidInputException("Short description cannot contain special characters");
        }
    }

    public static void validateTypeOfRepair(TypeOfRepair typeOfRepair) throws InvalidInputException {
        if (typeOfRepair == null || !EnumSet.allOf(TypeOfRepair.class).contains(typeOfRepair)) {
            throw new InvalidInputException("Invalid type of repair");
        }
    }

    public static void validateStatusOfRepair(StatusOfRepair statusOfRepair) throws InvalidInputException {
        if (statusOfRepair == null || !EnumSet.allOf(StatusOfRepair.class).contains(statusOfRepair)) {
            throw new InvalidInputException("Invalid status of repair");
        }
    }

    public static void validateCost(BigDecimal cost) throws InvalidInputException {
        if (cost == null || cost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Cost must be a positive non-zero value");
        }

        BigDecimal maxCost = new BigDecimal("1000000"); // Or any other maximum cost
        if (cost.compareTo(maxCost) > 0) {
            throw new InvalidInputException("Cost exceeds maximum allowed value");
        }
    }

    public static void validateDescriptionText(String descriptionText) throws InvalidInputException {
        if (descriptionText != null && descriptionText.length() > 1000) {
            throw new InvalidInputException("Description text length cannot exceed 1000 characters");
        }

        /*if (!descriptionText.matches("^[\p{L}0-9\\s.,!?]+$")) {
            throw new InvalidInputException("Description text cannot contain special characters");
        }*/
    }
}

