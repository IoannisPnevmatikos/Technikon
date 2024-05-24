package com.team1.technikon.controller;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.securityservice.service.UserInfoDetails;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/repair")
@AllArgsConstructor
public class RepairController {

    private final RepairService repairService;

    @PostMapping
    public ResponseEntity<RepairDto> create(@RequestBody RepairDto repairDto, Authentication authentication) throws InvalidInputException, EntityFailToCreateException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long ownerId = userInfoDetails.getId();
        RepairDto repair = repairService.createRepair(ownerId,repairDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(repair);
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<RepairDto>> getRepairsByDate(@PathVariable("date") LocalDate date, Authentication authentication) throws InvalidInputException, EntityNotFoundException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long ownerId = userInfoDetails.getId();
        List<RepairDto> repairs = repairService.getRepairByDate(ownerId,date);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/dateRange/{startDate}/{endDate}")
    public ResponseEntity<List<RepairDto>> getRepairsByDateRange(@PathVariable("startDate") LocalDate startDate,
                                                                 @PathVariable("endDate") LocalDate endDate, Authentication authentication) throws InvalidInputException, EntityNotFoundException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long ownerId = userInfoDetails.getId();
        List<RepairDto> repairs = repairService.getRepairByRangeOfDates(ownerId,startDate, endDate);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/tinNumber")
    public ResponseEntity<List<RepairDto>> searchRepairsByOwnerId(Authentication authentication) throws InvalidInputException, EntityNotFoundException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long ownerId = userInfoDetails.getId();
        List<RepairDto> repairs = repairService.searchByOwnerId(ownerId);
        return ResponseEntity.ok(repairs);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RepairDto> updateRepair(@PathVariable long id, @RequestBody RepairDto repairDto, Authentication authentication) throws InvalidInputException, EntityNotFoundException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long ownerId = userInfoDetails.getId();
        RepairDto repair = repairService.updateRepair(ownerId, id, repairDto);
        return ResponseEntity.ok(repair);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable long id, Authentication authentication) throws EntityNotFoundException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long ownerId = userInfoDetails.getId();
        repairService.deleteRepair(ownerId,id);
        return ResponseEntity.ok().build();
    }
}


