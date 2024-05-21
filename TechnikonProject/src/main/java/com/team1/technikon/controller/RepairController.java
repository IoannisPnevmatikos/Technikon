package com.team1.technikon.controller;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/repair")
@AllArgsConstructor
public class RepairController {

    private final RepairService repairService;

    @PostMapping
    public ResponseEntity<RepairDto> create(@RequestBody RepairDto repairDto) throws InvalidInputException, EntityFailToCreateException {
        RepairDto repair = repairService.createRepair(repairDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(repair);
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<RepairDto>> getRepairsByDate(@PathVariable("date") LocalDate date) throws InvalidInputException, EntityNotFoundException {
        List<RepairDto> repairs = repairService.getRepairByDate(date);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/dateRange/{startDate}/{endDate}")
    public ResponseEntity<List<RepairDto>> getRepairsByDateRange(@PathVariable("startDate") LocalDate startDate,
                                                                 @PathVariable("endDate") LocalDate endDate) throws InvalidInputException, EntityNotFoundException {
        List<RepairDto> repairs = repairService.getRepairByRangeOfDates(startDate, endDate);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/tinNumber/{tinNumber}")
    public ResponseEntity<List<RepairDto>> searchRepairsByTinNumber(@PathVariable String tinNumber) throws InvalidInputException, EntityNotFoundException {
        List<RepairDto> repairs = repairService.searchByOwnerTinNumber(tinNumber);
        return ResponseEntity.ok(repairs);
    }


    @PutMapping("/descText/{id}/{descText}")
    public ResponseEntity<RepairDto> updateRepairId(@PathVariable long id, @RequestBody RepairDto repairDto) throws InvalidInputException, EntityNotFoundException {
        RepairDto repair = repairService.updateRepair(id, repairDto);
        return ResponseEntity.ok(repair);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable long id) throws EntityNotFoundException {
        repairService.deleteRepair(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RepairDto>> getAllData() throws EntityNotFoundException {
        List<RepairDto> repairs = repairService.getAllData();
        return ResponseEntity.ok(repairs);
    }
}


