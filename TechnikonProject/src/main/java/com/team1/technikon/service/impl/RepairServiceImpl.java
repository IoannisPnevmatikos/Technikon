package com.team1.technikon.service.impl;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.mapper.TechnikonMapper;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.repository.RepairRepository;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseApi<Repair> createRepair(RepairDto repairDto) {
        Repair repair = technikonMapper.toRepair(repairDto);
        repairRepository.save(repair);
        return new ResponseApi<>(0,"New repair created!",repair);
    }

    // SEARCHES
    @Override
    public ResponseApi<List<RepairDto>> getRepairByDate(LocalDateTime localDateTime) {
        List<RepairDto> repairDtos = repairRepository.getRepairByDate(localDateTime)
                .stream()
                .map(technikonMapper::toRepairDto)
                .collect(Collectors.toList());

        if (!repairDtos.isEmpty()) {
            return new ResponseApi<>(0, "Repairs for the given date: ", repairDtos);
        } else {
            return new ResponseApi<>(0, "No repairs found for the given date!", null);
        }
    }

    @Override
    public ResponseApi<List<RepairDto>> getRepairByRangeOfDates(LocalDateTime startingDate, LocalDateTime endingDate) {
        List<Repair> repairs = repairRepository.getRepairByRangeOfDates(startingDate, endingDate);

        if (!repairs.isEmpty()) {
            List<RepairDto> repairDtos = repairs.stream()
                    .map(technikonMapper::toRepairDto)
                    .collect(Collectors.toList());
            return new ResponseApi<>(0, "Repairs for the given range of dates: ", repairDtos);
        } else {
            return new ResponseApi<>(0, "No repairs found for the given range of dates", null);
        }
    }


    @Override
    public ResponseApi<List<Repair>> searchByOwnerTinNumber(long tinNumber) {
        List<Repair> repairs = repairRepository.searchByOwnerTinNumber(tinNumber);

        if (!repairs.isEmpty()) {
            return new ResponseApi<>(0, "Repairs found for the given owner's TIN number: ", repairs);
        } else {
            return new ResponseApi<>(0, "No repairs found for the given owner's TIN number", null);
        }
    }

    // UPDATES
    @Override
    public ResponseApi<Repair> updateShortDescription(long id, String shortDescription) {
        int updateResult = repairRepository.updateShortDescription(id, shortDescription);

        if (updateResult == 1) {
            Repair updatedRepair = repairRepository.findById(id).orElse(null);
            if (updatedRepair != null) {
                return new ResponseApi<>(0, "Short description updated successfully", updatedRepair);
            } else {
                return new ResponseApi<>(0, "Repair not found with ID: " + id, null);
            }
        } else {
            return new ResponseApi<>(0, "Update failed for repair with ID: " + id, null);
        }
    }

    @Override
    public ResponseApi<Repair> updateDate(long id, LocalDateTime localDateTime) {
        int updateResult = repairRepository.updateDate(id, localDateTime);

        if (updateResult == 1) {
            Repair updatedRepair = repairRepository.findById(id).orElse(null);
            if (updatedRepair != null) {
                return new ResponseApi<>(0, "Repair updated successfully", updatedRepair);
            } else {
                return new ResponseApi<>(0, "Repair not found with ID: " + id, null);
            }
        } else {
            return new ResponseApi<>(0, "Update failed for repair with ID: " + id, null);
        }
    }

    @Override
    public ResponseApi<Repair> updateTypeOfRepair(long id, TypeOfRepair typeOfRepair) {
        int updateResult = repairRepository.updateTypeOfRepair(id, typeOfRepair);

        if (updateResult == 1) {
            Repair updatedRepair = repairRepository.findById(id).orElse(null);
            if (updatedRepair != null) {
                return new ResponseApi<>(0, "Type of repair updated successfully", updatedRepair);
            } else {
                return new ResponseApi<>(0, "Repair not found with ID: " + id, null);
            }
        } else {
            return new ResponseApi<>(0, "Update failed for repair with ID: " + id, null);
        }
    }

    @Override
    public ResponseApi<Repair> updateStatusOfRepair(long id, StatusOfRepair statusOfRepair) {
        int updateResult = repairRepository.updateStatusOfRepair(id, statusOfRepair);

        if (updateResult == 1) {
            Repair updatedRepair = repairRepository.findById(id).orElse(null);
            if (updatedRepair != null) {
                return new ResponseApi<>(0, "Status of repair updated successfully", updatedRepair);
            } else {
                return new ResponseApi<>(0, "Repair not found with ID: " + id, null);
            }
        } else {
            return new ResponseApi<>(0, "Update failed for repair with ID: " + id, null);
        }
    }

    @Override
    public ResponseApi<Repair> updateCost(long id, BigDecimal cost) {
        int updateResult = repairRepository.updateCost(id, cost);

        if (updateResult == 1) {
            Repair updatedRepair = repairRepository.findById(id).orElse(null);
            if (updatedRepair != null) {
                return new ResponseApi<>(0, "Cost updated successfully", updatedRepair);
            } else {
                return new ResponseApi<>(0, "Repair not found with ID: " + id, null);
            }
        } else {
            return new ResponseApi<>(0, "Update failed for repair with ID: " + id, null);
        }
    }

    @Override
    public ResponseApi<Repair> updateDescriptionText(long id, String descriptionText) {
        int updateResult = repairRepository.updateDescriptionText(id, descriptionText);

        if (updateResult == 1) {
            Repair updatedRepair = repairRepository.findById(id).orElse(null);
            if (updatedRepair != null) {
                return new ResponseApi<>(0, "Description text updated successfully", updatedRepair);
            } else {
                return new ResponseApi<>(0, "Repair not found with ID: " + id, null);
            }
        } else {
            return new ResponseApi<>(0, "Update failed for repair with ID: " + id, null);
        }
    }

    @Override
    public ResponseApi<Repair> deleteRepair(long id) {
        Repair repair = repairRepository.findById(id).orElse(null);

        if (repair == null) {
            return new ResponseApi<>(0, "Repair not found with ID: " + id, null);
        }

        if (!repair.getStatusOfRepair().equals(StatusOfRepair.PENDING)) {
            return new ResponseApi<>(0, "Cannot delete repair with ID: " + id + ". It is not in PENDING status", null);
        }

        repairRepository.deleteById(id);
        return new ResponseApi<>(0, "Repair deleted successfully", repair);
    }

    @Override
    public ResponseApi<List<Repair>> getAllData() {
        List<Repair> allRepairs = repairRepository.findAll();

        if (!allRepairs.isEmpty()) {
            return new ResponseApi<>(0, "Repairs found:", allRepairs);
        } else {
            return new ResponseApi<>(0, "No repairs found", null);
        }
    }
}


