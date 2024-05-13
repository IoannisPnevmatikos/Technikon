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
    ResponseApi<Repair> createRepair(RepairDto repairDto);
    ResponseApi<List<RepairDto>> getRepairByDate(LocalDateTime localDateTime);
    ResponseApi<List<RepairDto>> getRepairByRangeOfDates(LocalDateTime startingDate, LocalDateTime endingDate);
    ResponseApi<List<Repair>> searchByOwnerTinNumber(long tinNumber);
    ResponseApi<Repair> updateDate(long id, LocalDateTime localDateTime);
    ResponseApi<Repair> updateShortDescription(long id, String shortDescription);
    ResponseApi<Repair> updateTypeOfRepair(long id, TypeOfRepair typeOfRepair);
    ResponseApi<Repair> updateStatusOfRepair(long id, StatusOfRepair statusOfRepair);
    ResponseApi<Repair> updateCost(long id, BigDecimal cost);
    ResponseApi<Repair> updateDescriptionText(long id, String descriptionText);
    ResponseApi<Repair> deleteRepair(long id);
    ResponseApi<List<Repair>> getAllData();
}
