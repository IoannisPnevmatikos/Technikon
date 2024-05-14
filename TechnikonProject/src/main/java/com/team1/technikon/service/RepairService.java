package com.team1.technikon.service;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RepairService {
    RepairDto createRepair(RepairDto repairDto);
    List<RepairDto> getRepairByDate(LocalDate localDate);
    List<RepairDto> getRepairByRangeOfDates(LocalDate startingDate, LocalDate endingDate);
    List<RepairDto> searchByOwnerTinNumber(long tinNumber);
    RepairDto updateProperty(long id, RepairDto repairDto);
    /*RepairDto updateDate(long id, LocalDate localDate);
    RepairDto updateShortDescription(long id, String shortDescription);
    RepairDto updateTypeOfRepair(long id, TypeOfRepair typeOfRepair);
    RepairDto updateStatusOfRepair(long id, StatusOfRepair statusOfRepair);
    RepairDto updateCost(long id, BigDecimal cost);
    RepairDto updateDescriptionText(long id, String descriptionText);*/
    void deleteRepair(long id);
    List<RepairDto> getAllData();
}
