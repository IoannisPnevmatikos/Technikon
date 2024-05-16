package com.team1.technikon.dto;

import com.team1.technikon.model.enums.StatusOfRepair;

import java.math.BigDecimal;

public record RepairReportDto(StatusOfRepair statusOfRepair, BigDecimal totalCost, int numberOfRepairs) {
}

