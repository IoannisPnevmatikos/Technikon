package com.team1.technikon.service.impl;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.mapper.TechnikonMapper;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {


    private final RepairRepository repairRepository;
    private final TechnikonMapper technikonMapper;

    // CREATE
    @Override
    public Repair createRepair(RepairDto repairDto) {
        Repair repair = technikonMapper.toRepair(repairDto);
        repairRepository.save(repair);
        return repair;
    }

    // SEARCHES
    @Override
    public List<RepairDto> getRepairByDate(LocalDateTime localDateTime) {
        return repairRepository.getRepairByDate(localDateTime).stream()
                .map(technikonMapper::toRepairDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairDto> getRepairByRangeOfDates(LocalDateTime startingDate, LocalDateTime endingDate) {
        return repairRepository.getRepairByRangeOfDates(startingDate, endingDate).stream()
                .map(technikonMapper::toRepairDto)
                .collect(Collectors.toList());
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
        if(!repair.getStatusOfRepair().equals(StatusOfRepair.PENDING)) return false;
        repairRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Repair> getAllData() {
        return repairRepository.findAll();
    }
}

