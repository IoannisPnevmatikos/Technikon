package com.team1.technikon.service;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface RepairService {
    Repair createRepair(RepairDto repairDto);
    List<RepairDto> getRepairByDate(LocalDateTime localDateTime);
    List<RepairDto> getRepairByRangeOfDates(LocalDateTime startingDate, LocalDateTime endingDate);
    List<Repair> searchByOwnerTinNumber(long tinNumber);
    boolean updateDate(long id, LocalDateTime localDateTime);
    boolean updateShortDescription(long id, String shortDescription);
    boolean updateTypeOfRepair(long id, TypeOfRepair typeOfRepair);
    boolean updateStatusOfRepair(long id, StatusOfRepair statusOfRepair);
    boolean updateCost(long id, BigDecimal cost);
    boolean updateDescriptionText(long id, String descriptionText);
    boolean deleteRepair(long id);
    List<Repair> getAllData();
}
