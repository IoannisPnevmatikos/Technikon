package com.team1.technikon.controller;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;

    @PostMapping
    public Repair create(@RequestBody RepairDto repairDto) {
        return repairService.createRepair(repairDto);
    }

//    @GetMapping("/{date}")
//    public List<Repair> getRepairsByDate(@PathVariable String date) {
//        // Service impl must turn string into LocalDateTime
//        // return repairService.getRepairByDate(localDateTime);
//        return repairService.getRepairByDate(date);
//
//    }
//
//    @GetMapping("/dateRange/{startDate}/{endDate}")
//    public List<Repair> getRepairsByDateRange(@PathVariable String startDate, @PathVariable String endDate) {
//        // Service impl must turn string into LocalDateTime
//        // return repairService.getRepairByDate(localDateTime);
//        return repairService.getRepairByRangeOfDates(startDate, endDate);
//    }

    @GetMapping("/tinNumber/{tinNumber}")
    public List<Repair> searchRepairsByTinNumber(@PathVariable long tinNumber) {
        return repairService.searchByOwnerTinNumber(tinNumber);
    }

//    @PutMapping("/date/{id}/{date}")
//    public boolean updateDate(@PathVariable long id, @PathVariable String date) {
//        // Service impl must turn string into LocalDateTime
//
//        return repairService.updateDate(id, date);
//    }

    @PutMapping("/description/{id}")
    public boolean updateShortDesc(@PathVariable long id, @RequestBody String desc) {
        return repairService.updateShortDescription(id, desc);
    }

    @PutMapping("/typeOfRepair/{id}/{typeOfRepair}")
    public boolean updateTypeOfRepair(@PathVariable long id, @PathVariable TypeOfRepair typeOfRepair) {
        return repairService.updateTypeOfRepair(id, typeOfRepair);
    }

    @PutMapping("/statusOfRepair/{id}/{statusOfRepair}")
    public boolean updateStatusOfRepair(@PathVariable long id, @PathVariable StatusOfRepair statusOfRepair) {
        return repairService.updateStatusOfRepair(id, statusOfRepair);
    }

    @PutMapping("/cost/{id}/{cost}")
    public boolean updateCost(@PathVariable long id, @PathVariable BigDecimal cost) {
        return repairService.updateCost(id, cost);
    }

    @PutMapping("/descText/{id}/{descText}")
    public boolean updateDescText(@PathVariable long id, @PathVariable String descText) {
        return repairService.updateDescriptionText(id, descText);
    }

    @DeleteMapping("/{id}")
    public boolean deleteRepair(@PathVariable long id) {
        return repairService.deleteRepair(id);
    }

    @GetMapping
    public List<Repair> getAllData() {
        return repairService.getAllData();
    }
}
