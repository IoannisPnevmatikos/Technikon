package com.team1.technikon.service;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.model.Property;
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
    Repair updateDate(long id, LocalDateTime localDateTime);
    Repair updateShortDescription(long id, String shortDescription);
    Repair updateTypeOfRepair(long id, TypeOfRepair typeOfRepair);
    Repair updateStatusOfRepair(long id, StatusOfRepair statusOfRepair);
    Repair updateCost(long id, BigDecimal cost);
    Repair updateDescriptionText(long id, String descriptionText);
    void deleteRepair(long id);
    List<Repair> getAllData();
}
