package com.team1.technikon.service;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface RepairService {
    RepairDto createRepair(RepairDto repairDto);
    List<RepairDto> getRepairByDate(LocalDateTime localDateTime);
    List<RepairDto> getRepairByRangeOfDates(LocalDateTime startingDate, LocalDateTime endingDate);
    List<RepairDto> searchByOwnerTinNumber(long tinNumber);
    RepairDto updateDate(long id, LocalDateTime localDateTime);
    RepairDto updateShortDescription(long id, String shortDescription);
    RepairDto updateTypeOfRepair(long id, TypeOfRepair typeOfRepair);
    RepairDto updateStatusOfRepair(long id, StatusOfRepair statusOfRepair);
    RepairDto updateCost(long id, BigDecimal cost);
    RepairDto updateDescriptionText(long id, String descriptionText);
    void deleteRepair(long id);
    List<RepairDto> getAllData();
}
