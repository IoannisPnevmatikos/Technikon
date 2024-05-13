package com.team1.technikon.controller;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @GetMapping("/{date}")
    public ResponseEntity<List<RepairDto>> getRepairsByDate(@PathVariable("date") LocalDateTime date) {
        List<RepairDto> repairs = repairService.getRepairByDate(date);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/dateRange/{startDate}/{endDate}")
    public ResponseEntity<List<RepairDto>> getRepairsByDateRange(@PathVariable("startDate") LocalDateTime startDate,
                                                                 @PathVariable("endDate") LocalDateTime endDate) {
        List<RepairDto> repairs = repairService.getRepairByRangeOfDates(startDate, endDate);
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/tinNumber/{tinNumber}")
    public ResponseEntity<List<RepairDto>> searchRepairsByTinNumber(@PathVariable long tinNumber) {
        List<RepairDto> repairs = repairService.searchByOwnerTinNumber(tinNumber);
        return ResponseEntity.ok(repairs);
    }

    @PutMapping("/date/{id}/{date}")
    public ResponseEntity<RepairDto> updateDate(@PathVariable long id, @RequestBody LocalDateTime date) {
        RepairDto repair = repairService.updateDate(id, date);
        return ResponseEntity.ok(repair);
    }

    @PutMapping("/description/{id}")
    public ResponseEntity<RepairDto> updateShortDesc(@PathVariable long id, @RequestBody String desc) {
        RepairDto repair = repairService.updateShortDescription(id, desc);
        return ResponseEntity.ok(repair);
    }

    @PutMapping("/typeOfRepair/{id}/{typeOfRepair}")
    public ResponseEntity<RepairDto> updateTypeOfRepair(@PathVariable long id, @PathVariable TypeOfRepair typeOfRepair) {
        RepairDto repair = repairService.updateTypeOfRepair(id, typeOfRepair);
        return ResponseEntity.ok(repair);
    }

    @PutMapping("/statusOfRepair/{id}/{statusOfRepair}")
    public ResponseEntity<RepairDto> updateStatusOfRepair(@PathVariable long id, @PathVariable StatusOfRepair statusOfRepair) {
        RepairDto repair = repairService.updateStatusOfRepair(id, statusOfRepair);
        return ResponseEntity.ok(repair);
    }

    @PutMapping("/cost/{id}/{cost}")
    public ResponseEntity<RepairDto> updateCost(@PathVariable long id, @PathVariable BigDecimal cost) {
        RepairDto repair = repairService.updateCost(id, cost);
        return ResponseEntity.ok(repair);
    }

    @PutMapping("/descText/{id}/{descText}")
    public ResponseEntity<RepairDto> updateDescText(@PathVariable long id, @RequestBody String descText) {
        RepairDto repair = repairService.updateDescriptionText(id, descText);
        return ResponseEntity.ok(repair);
    }

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




/*
package com.team1.technikon.controller;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@AllArgsConstructor
@RestController
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;

    @PostMapping
    public ResponseApi<Repair> create(@RequestBody RepairDto repairDto) {
        return repairService.createRepair(repairDto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "Date in ISO format (YYYY-MM-DDThh:mm:ss)", required = true, dataType = "string", paramType = "path", example = "2024-05-11T08:00:00", format = "date-time")
    })

    @GetMapping("/{date}")
    public ResponseApi<List<RepairDto>> getRepairsByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return repairService.getRepairByDate(date);
    }

    @GetMapping("/dateRange/{startDate}/{endDate}")
    public ResponseApi<List<RepairDto>> getRepairsByDateRange(@PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                              @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return repairService.getRepairByRangeOfDates(startDate, endDate);
    }

    @PutMapping("/date/{id}/{date}")
    public ResponseApi<Repair> updateDate(@PathVariable long id, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return repairService.updateDate(id, date);
    }

    @GetMapping("/tinNumber/{tinNumber}")
    public ResponseApi<List<Repair>> searchRepairsByTinNumber(@PathVariable long tinNumber) {
        return repairService.searchByOwnerTinNumber(tinNumber);
    }

    @PutMapping("/description/{id}")
    public ResponseApi<Repair> updateShortDesc(@PathVariable long id, @RequestBody String desc) {
        return repairService.updateShortDescription(id, desc);
    }

    @PutMapping("/typeOfRepair/{id}/{typeOfRepair}")
    public ResponseApi<Repair> updateTypeOfRepair(@PathVariable long id, @PathVariable TypeOfRepair typeOfRepair) {
        return repairService.updateTypeOfRepair(id, typeOfRepair);
    }

    @PutMapping("/statusOfRepair/{id}/{statusOfRepair}")
    public ResponseApi<Repair> updateStatusOfRepair(@PathVariable long id, @PathVariable StatusOfRepair statusOfRepair) {
        return repairService.updateStatusOfRepair(id, statusOfRepair);
    }

    @PutMapping("/cost/{id}/{cost}")
    public ResponseApi<Repair> updateCost(@PathVariable long id, @PathVariable BigDecimal cost) {
        return repairService.updateCost(id, cost);
    }

    @PutMapping("/descText/{id}/{descText}")
    public ResponseApi<Repair> updateDescText(@PathVariable long id, @PathVariable String descText) {
        return repairService.updateDescriptionText(id, descText);
    }

    @DeleteMapping("/{id}")
    public ResponseApi<Repair> deleteRepair(@PathVariable long id) {
        return repairService.deleteRepair(id);
    }

    @GetMapping
    public ResponseApi<List<Repair>> getAllData() {
        return repairService.getAllData();
    }
}
*/
