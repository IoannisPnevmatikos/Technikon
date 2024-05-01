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

    private RepairRepository repairRepository;

    @Override
    public Repair createRepair(RepairDto repairDto) {
        return null;
    }

    @Override
    public List<Repair> getRepairByDate(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public List<Repair> getRepairByRangeOfDates(LocalDateTime startingDate, LocalDateTime endingDate) {
        return null;
    }

    @Override
    public List<Repair> searchByOwnerTinNumber(long tinNumber) {
        return null;
    }

    @Override
    public boolean updateDate(long id, LocalDateTime localDateTime) {
        return false;
    }

    @Override
    public boolean updateShortDescription(long id, String shortDescription) {
        return false;
    }

    @Override
    public boolean updateTypeOfRepair(long id, TypeOfRepair typeOfRepair) {
        return false;
    }

    @Override
    public boolean updateStatusOfRepair(long id, StatusOfRepair statusOfRepair) {
        return false;
    }

    @Override
    public boolean updateCost(long id, BigDecimal cost) {
        return false;
    }

    @Override
    public boolean updateDescriptionText(long id, String descriptionText) {
        return false;
    }

    @Override
    public boolean deleteRepair(long id) {
        return false;
    }
}
