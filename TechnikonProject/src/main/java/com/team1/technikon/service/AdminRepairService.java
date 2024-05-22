package com.team1.technikon.service;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.RepairReportDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import java.util.List;

public interface AdminRepairService {

    RepairDto createRepair(RepairDto repairDto) throws EntityFailToCreateException, InvalidInputException;

    List<RepairDto> getRepairByDate(LocalDate localDate) throws EntityNotFoundException, InvalidInputException;

    List<RepairDto> getRepairByRangeOfDates(LocalDate startingDate, LocalDate endingDate) throws EntityNotFoundException, InvalidInputException;

    List<RepairDto> searchByOwnerTinNumber(String tinNumber) throws InvalidInputException, EntityNotFoundException;

    RepairDto updateRepair(long id, RepairDto repairDto) throws EntityNotFoundException, InvalidInputException;

    void deleteRepair(long id) throws IllegalStateException, EntityNotFoundException;

    List<RepairDto> getAllData() throws EntityNotFoundException;

    List<RepairReportDto> getRepairReport(LocalDate startingDate, LocalDate endingDate) throws InvalidInputException, EntityNotFoundException;
}
