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

    @PostMapping("/create")
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
//    @GetMapping("/dateRange")
//    public List<Repair> getRepairsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
//        // Service impl must turn string into LocalDateTime
//        // return repairService.getRepairByDate(localDateTime);
//        return repairService.getRepairByRangeOfDates(startDate, endDate);
//    }

    @GetMapping("/search/{tinNumber}")
    public List<Repair> searchRepairsByTinNumber(@PathVariable long tinNumber) {
        return repairService.searchByOwnerTinNumber(tinNumber);
    }

//    @PutMapping("/updateDate/{id}")
//    public boolean updateDate(@PathVariable long id, @RequestBody String date) {
//        // Service impl must turn string into LocalDateTime
//
//        return repairService.updateDate(id, date);
//    }

    @PutMapping("/updateDesc/{id}")
    public boolean updateShortDesc(@PathVariable long id, @RequestBody String desc) {
        return repairService.updateShortDescription(id, desc);
    }

    @PutMapping("/updateTypeOfRepair/{id}")
    public boolean updateTypeOfRepair(@PathVariable long id, @RequestBody TypeOfRepair typeOfRepair) {
        return repairService.updateTypeOfRepair(id, typeOfRepair);
    }

    @PutMapping("/updateStatusOfRepair/{id}")
    public boolean updateStatusOfRepair(@PathVariable long id, @RequestBody StatusOfRepair statusOfRepair) {
        return repairService.updateStatusOfRepair(id, statusOfRepair);
    }

    @PutMapping("/updateCost/{id}")
    public boolean updateCost(@PathVariable long id, @RequestBody BigDecimal cost) {
        return repairService.updateCost(id, cost);
    }

    @PutMapping("/updateDescText/{id}")
    public boolean updateDescText(@PathVariable long id, @RequestBody String descText) {
        return repairService.updateDescriptionText(id, descText);
    }

    @PutMapping("/delete/{id}")
    public boolean deleteRepair(@PathVariable long id) {
        return repairService.deleteRepair(id);
    }

    @GetMapping("/allData")
    public List<Repair> getAllData() {
        return repairService.getAllData();
    }
}
