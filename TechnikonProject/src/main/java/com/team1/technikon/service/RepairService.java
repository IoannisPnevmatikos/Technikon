package com.team1.technikon.service;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.RepairReportDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;

import java.time.LocalDate;
import java.util.List;

public interface RepairService {
    RepairDto createRepair(long ownerId,RepairDto repairDto) throws EntityFailToCreateException, InvalidInputException;

    List<RepairDto> getRepairByDate(long ownerId,LocalDate localDate) throws EntityNotFoundException, InvalidInputException;

    List<RepairDto> getRepairByRangeOfDates(long ownerId,LocalDate startingDate, LocalDate endingDate) throws EntityNotFoundException, InvalidInputException;

    // ousiastika myrepairs gia ton owner
    List<RepairDto> searchByOwnerId(long id) throws InvalidInputException, EntityNotFoundException;

    RepairDto updateRepair(long ownerId, long id, RepairDto repairDto) throws EntityNotFoundException, InvalidInputException;

    void deleteRepair(long ownerId, long id) throws EntityNotFoundException;

    //List<RepairDto> getAllData() throws EntityNotFoundException;

    //List<RepairReportDto> getRepairReport(LocalDate startingDate, LocalDate endingDate) throws InvalidInputException, EntityNotFoundException;
}
