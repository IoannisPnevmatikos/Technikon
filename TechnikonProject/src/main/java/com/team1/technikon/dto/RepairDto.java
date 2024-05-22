package com.team1.technikon.dto;

import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RepairDto(
        LocalDate localDate,
        String shortDescription,
        TypeOfRepair typeOfRepair,
        StatusOfRepair statusOfRepair,
        BigDecimal cost,
        String descriptionText,
        String propertyId // Use property ID = E9 instead of the full Property object
) {
}

