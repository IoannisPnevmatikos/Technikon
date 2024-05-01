package com.team1.technikon.service;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface RepairService {
    Repair createRepair(RepairDto repairDto);
    Repair getRepairByDate(LocalDateTime localDateTime);
    Repair getRepairByRangeOfDates(LocalDateTime startingDate, LocalDateTime endingDate);
    Repair searchByOwnerTinNumber(long tinNumber);
    boolean updateDate(long id, LocalDateTime localDateTime);
    boolean updateShortDescription(long id, String shortDescription);
    boolean updateTypeOfRepair(long id, TypeOfRepair typeOfRepair);
    boolean updateStatusOfRepair(long id, StatusOfRepair statusOfRepair);
    boolean updateCost(long id, BigDecimal cost);
    boolean updateDescriptionText(long id, String descriptionText);
    boolean deleteRepair(long id);
}
