package com.team1.technikon.service;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.RepairReportDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RepairService {
    RepairDto createRepair(RepairDto repairDto) throws EntityFailToCreateException, InvalidInputException;
    List<RepairDto> getRepairByDate(LocalDate localDate) throws EntityNotFoundException, InvalidInputException;
    List<RepairDto> getRepairByRangeOfDates(LocalDate startingDate, LocalDate endingDate) throws EntityNotFoundException, InvalidInputException;
    List<RepairDto> searchByOwnerTinNumber(String tinNumber) throws InvalidInputException, EntityNotFoundException;
    RepairDto updateRepair(long id, RepairDto repairDto) throws EntityNotFoundException, InvalidInputException;
    /*RepairDto updateDate(long id, LocalDate localDate);
    RepairDto updateShortDescription(long id, String shortDescription);
    RepairDto updateTypeOfRepair(long id, TypeOfRepair typeOfRepair);
    RepairDto updateStatusOfRepair(long id, StatusOfRepair statusOfRepair);
    RepairDto updateCost(long id, BigDecimal cost);
    RepairDto updateDescriptionText(long id, String descriptionText);*/
    void deleteRepair(long id) throws EntityNotFoundException;
    List<RepairDto> getAllData() throws EntityNotFoundException;
    List<RepairReportDto> getRepairReport(LocalDate startingDate, LocalDate endingDate) throws InvalidInputException, EntityNotFoundException;
}
