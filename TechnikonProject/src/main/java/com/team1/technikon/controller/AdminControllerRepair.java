package com.team1.technikon.controller;


import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.RepairReportDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.service.AdminRepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/admin")
public class AdminControllerRepair {

    private final AdminRepairService adminRepairService;

    @PostMapping("/repair")
    public ResponseEntity<RepairDto> create(@RequestBody RepairDto repairDto) throws InvalidInputException, EntityFailToCreateException {
        RepairDto repair = adminRepairService.createRepair(repairDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(repair);
    }

    @GetMapping("/repair/{date}")
    public ResponseEntity<List<RepairDto>> getRepairsByDate(@PathVariable("date") LocalDate date) throws InvalidInputException, EntityNotFoundException {
        List<RepairDto> repairs = adminRepairService.getRepairByDate(date);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/repair/dateRange/{startDate}/{endDate}")
    public ResponseEntity<List<RepairDto>> getRepairsByDateRange(@PathVariable("startDate") LocalDate startDate,
                                                                 @PathVariable("endDate") LocalDate endDate) throws InvalidInputException, EntityNotFoundException {
        List<RepairDto> repairs = adminRepairService.getRepairByRangeOfDates(startDate, endDate);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/repair/tinNumber/{tinNumber}")
    public ResponseEntity<List<RepairDto>> searchRepairsByTinNumber(@PathVariable String tinNumber) throws InvalidInputException, EntityNotFoundException {
        List<RepairDto> repairs = adminRepairService.searchByOwnerTinNumber(tinNumber);
        return ResponseEntity.ok(repairs);
    }

    @PutMapping("/repair/{id}")
    public ResponseEntity<RepairDto> updateRepairId(@PathVariable long id, @RequestBody RepairDto repairDto) throws InvalidInputException, EntityNotFoundException {
        RepairDto repair = adminRepairService.updateRepair(id, repairDto);
        return ResponseEntity.ok(repair);
    }

    @DeleteMapping("/repair/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable long id) throws EntityNotFoundException, UnauthorizedAccessException {
        adminRepairService.deleteRepair(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/repair")
    public ResponseEntity<List<RepairDto>> getAllData() throws EntityNotFoundException {
        List<RepairDto> repairs = adminRepairService.getAllData();
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/repair/report/dateRange/{startDate}/{endDate}")
    public ResponseEntity<List<RepairReportDto>> getRepairReport(@PathVariable("startDate") LocalDate startDate,
                                                           @PathVariable("endDate") LocalDate endDate) throws EntityNotFoundException, InvalidInputException {
        List<RepairReportDto> repairs = adminRepairService.getRepairReport(startDate,endDate);
        return ResponseEntity.ok(repairs);
    }
}

