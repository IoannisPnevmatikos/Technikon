package com.team1.technikon.service.impl;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.repository.RepairRepository;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {


    private final RepairRepository repairRepository;

    // CREATE
    @Override
    public Repair createRepair(RepairDto repairDto) {
        Repair repair = new Repair();
        repair.setLocalDateTime(repairDto.localDateTime());
        repair.setShortDescription(repairDto.shortDescription());
        repair.setTypeOfRepair(repairDto.typeOfRepair());
        repair.setStatusOfRepair(repairDto.statusOfRepair());
        repair.setCost(repairDto.cost());
        repair.setDescriptionText(repairDto.descriptionText());
        repair.setOwner(repairDto.owner());
        repair.setProperty(repairDto.property());
        repairRepository.save(repair);
        return repair;
    }

    // SEARCHES
    @Override
    public List<Repair> getRepairByDate(LocalDateTime localDateTime) {
        return repairRepository.getRepairByDate(localDateTime);
    }

    @Override
    public List<Repair> getRepairByRangeOfDates(LocalDateTime startingDate, LocalDateTime endingDate) {
        return repairRepository.getRepairByRangeOfDates(startingDate, endingDate);
    }

    @Override
    public List<Repair> searchByOwnerTinNumber(long tinNumber) {
        return repairRepository.searchByOwnerTinNumber(tinNumber);
    }

    // UPDATES
    @Override
    public boolean updateDate(long id, LocalDateTime localDateTime) {
        return repairRepository.updateDate(id, localDateTime) == 1;
    }


    @Override
    public boolean updateShortDescription(long id, String shortDescription) {
        return repairRepository.updateShortDescription(id, shortDescription) == 1;
    }

    @Override
    public boolean updateTypeOfRepair(long id, TypeOfRepair typeOfRepair) {
        return repairRepository.updateTypeOfRepair(id, typeOfRepair) == 1;
    }

    @Override
    public boolean updateStatusOfRepair(long id, StatusOfRepair statusOfRepair) {
        return repairRepository.updateStatusOfRepair(id, statusOfRepair) == 1;
    }

    @Override
    public boolean updateCost(long id, BigDecimal cost) {
        return repairRepository.updateCost(id, cost) == 1;
    }

    @Override
    public boolean updateDescriptionText(long id, String descriptionText) {
        return repairRepository.updateDescriptionText(id, descriptionText) == 1;
    }

    @Override
    public boolean deleteRepair(long id) {
        Repair repair = repairRepository.findById(id).orElse(null);
        if(repair == null) return false;
        repairRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Repair> getAllData() {
        return repairRepository.findAll();
    }
}

