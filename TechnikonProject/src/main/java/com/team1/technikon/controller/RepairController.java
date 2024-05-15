package com.team1.technikon.controller;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/repair")
@AllArgsConstructor
public class RepairController {

    private final RepairService repairService;

    @PostMapping
    public ResponseEntity<RepairDto> create(@RequestBody RepairDto repairDto) {
        RepairDto repair = repairService.createRepair(repairDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(repair);
    }

    //NEED DATA VALIDATION EVERYWHERE
    @GetMapping("/{date}")
    public ResponseEntity<List<RepairDto>> getRepairsByDate(@PathVariable("date") LocalDate date) {
        List<RepairDto> repairs = repairService.getRepairByDate(date);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/dateRange/{startDate}/{endDate}")
    public ResponseEntity<List<RepairDto>> getRepairsByDateRange(@PathVariable("startDate") LocalDate startDate,
                                                                 @PathVariable("endDate") LocalDate endDate) {
        List<RepairDto> repairs = repairService.getRepairByRangeOfDates(startDate, endDate);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/tinNumber/{tinNumber}")
    public ResponseEntity<List<RepairDto>> searchRepairsByTinNumber(@PathVariable String tinNumber) {
        List<RepairDto> repairs = repairService.searchByOwnerTinNumber(tinNumber);
        return ResponseEntity.ok(repairs);
    }



    /*@PutMapping("/descText/{id}/{descText}")
    public ResponseEntity<RepairDto> updateDescText(@PathVariable long id, @RequestBody String descText) {
        RepairDto repair = repairService.updateDescriptionText(id, descText);
        return ResponseEntity.ok(repair);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable long id) {
        repairService.deleteRepair(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RepairDto>> getAllData() {
        List<RepairDto> repairs = repairService.getAllData();
        return ResponseEntity.ok(repairs);
    }
}


