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
