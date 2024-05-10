package com.team1.technikon.dto;

import com.team1.technikon.model.Property;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RepairDto(LocalDateTime localDateTime, String shortDescription, TypeOfRepair typeOfRepair,
                        StatusOfRepair statusOfRepair, BigDecimal cost, String descriptionText, Property property) {
}
