package com.team1.technikon.service.impl;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.mapper.TechnikonMapper;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.model.enums.TypeOfRepair;
import com.team1.technikon.repository.RepairRepository;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {

    private static final Logger logger = LoggerFactory.getLogger(RepairServiceImpl.class);

    private final RepairRepository repairRepository;
    private final TechnikonMapper technikonMapper;

    @Override
    public RepairDto createRepair(RepairDto repairDto) {
        logger.info("Creating a repair: {}", repairDto);

        //VALIDATE ENTITY REPAIR ATTRIBUTES
        validateCreateRepair(repairDto);

        //VALIDATE DATABASE
        try {
            Repair repair = technikonMapper.toRepair(repairDto);
            return technikonMapper.toRepairDto(repairRepository.save(repair));
        } catch (Exception e) {
            logger.error("Failed to create repair: {}", e.getMessage());
            throw new RuntimeException("Failed to create repair: " + e.getMessage());
        }
    }

    @Override
    public List<RepairDto> getRepairByDate(LocalDate localDate) {
        logger.info("Fetching repairs by date: {}", localDate);
        Repair repair = repairRepository.findByLocalDate(localDate)
                .orElseThrow(() -> {
                    logger.warn("No repair found for the given date: {}", localDate);
                    throw new NoSuchElementException("No repair found for the given date!");
                });
        return Collections.singletonList(technikonMapper.toRepairDto(repair));
    }

    @Override
    public List<RepairDto> getRepairByRangeOfDates(LocalDate startingDate, LocalDate endingDate) {
        logger.info("Fetching repairs by date range: {} to {}", startingDate, endingDate);
        List<Repair> repairs = repairRepository.findByLocalDateBetween(startingDate, endingDate);
        if (repairs.isEmpty()) {
            logger.warn("No repairs found for the given range of dates: {} to {}", startingDate, endingDate);
            throw new NoSuchElementException("No repairs found for the given range of dates!");
        }
        return repairs.stream().map(technikonMapper::toRepairDto).collect(Collectors.toList());
    }

    @Override
    public List<RepairDto> searchByOwnerTinNumber(String tinNumber) {
        logger.info("Searching repairs by owner's TIN number: {}", tinNumber);
        List<Repair> repairs = repairRepository.findByPropertyOwnerTinNumber(tinNumber);
        if (repairs.isEmpty()) {
            logger.warn("No repairs found for the given owner's TIN number: {}", tinNumber);
            throw new NoSuchElementException("No repairs found for the given owner's TIN number!");
        }
        return repairs.stream().map(technikonMapper::toRepairDto).collect(Collectors.toList());
    }

    @Override
    public RepairDto updateProperty(long id, RepairDto repairDto) {
        logger.info("Updating repair for repair ID {}", id);
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new NoSuchElementException("Repair not found with ID: " + id);
                });
        repair = technikonMapper.toRepairNonNull(repairDto);
        repairRepository.save(repair);
        return technikonMapper.toRepairDto(repair);
    }

    /*@Override
    public RepairDto updateDate(long id, LocalDate localDate) {
        logger.info("Updating repair date for repair ID {}: {}", id, localDate);
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new NoSuchElementException("Repair not found with ID: " + id);
                });
        repair.setLocalDate(localDate);
        return technikonMapper.toRepairDto(repairRepository.save(repair));
    }

    @Override
    public RepairDto updateShortDescription(long id, String shortDescription) {
        logger.info("Updating repair short description for repair ID {}: {}", id, shortDescription);
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new NoSuchElementException("Repair not found with ID: " + id);
                });
        repair.setShortDescription(shortDescription);
        return technikonMapper.toRepairDto(repairRepository.save(repair));
    }

    @Override
    public RepairDto updateTypeOfRepair(long id, TypeOfRepair typeOfRepair) {
        logger.info("Updating repair type of repair for repair ID {}: {}", id, typeOfRepair);
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new NoSuchElementException("Repair not found with ID: " + id);
                });
        repair.setTypeOfRepair(typeOfRepair);
        return technikonMapper.toRepairDto(repairRepository.save(repair));
    }

    @Override
    public RepairDto updateStatusOfRepair(long id, StatusOfRepair statusOfRepair) {
        logger.info("Updating repair status of repair for repair ID {}: {}", id, statusOfRepair);
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new NoSuchElementException("Repair not found with ID: " + id);
                });
        repair.setStatusOfRepair(statusOfRepair);
        return technikonMapper.toRepairDto(repairRepository.save(repair));
    }

    @Override
    public RepairDto updateCost(long id, BigDecimal cost) {
        logger.info("Updating repair cost for repair ID {}: {}", id, cost);
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new NoSuchElementException("Repair not found with ID: " + id);
                });
        repair.setCost(cost);
        return technikonMapper.toRepairDto(repairRepository.save(repair));
    }

    @Override
    public RepairDto updateDescriptionText(long id, String descriptionText) {
        logger.info("Updating repair description text for repair ID {}: {}", id, descriptionText);
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new NoSuchElementException("Repair not found with ID: " + id);
                });
        repair.setDescriptionText(descriptionText);
        return technikonMapper.toRepairDto(repairRepository.save(repair));
    }*/

    @Override
    public void deleteRepair(long id) {
        logger.info("Deleting repair with ID: {}", id);
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new NoSuchElementException("Repair not found with ID: " + id);
                });
        if (!repair.getStatusOfRepair().equals(StatusOfRepair.PENDING)) {
            logger.error("Cannot delete repair with ID: {}. It is not in PENDING status", id);
            throw new IllegalStateException("Cannot delete repair with ID: " + id + ". It is not in PENDING status");
        }
        repairRepository.delete(repair);
    }

    @Override
    public List<RepairDto> getAllData() {
        logger.info("Fetching all repairs");
        List<Repair> allRepairs = repairRepository.findAll();
        if (allRepairs.isEmpty()) {
            logger.warn("No repairs found!");
            throw new NoSuchElementException("No repairs found!");
        }
        return allRepairs.stream()
                .map(technikonMapper::toRepairDto)
                .collect(Collectors.toList());
    }

    //VALIDATE METHODS FOR ENTITY REPAIR
    //EDW OUSIASTIKA THA EXOUME VALIDATION LOGIKO, OXI TYPE VALIDATION(GINETAI STO FRONT END)
    //DIOTI PERNAEI HDH STO DTO STON CONTROLLER SAN SWSTOY TYPOU
    private void validateCreateRepair(RepairDto repairDto) {
        validateLocalDate(repairDto.localDate());
        validateShortDescription(repairDto.shortDescription());
        validateTypeOfRepair(repairDto.typeOfRepair());
        validateStatusOfRepair(repairDto.statusOfRepair());
        validateCost(repairDto.cost());
        validateDescriptionText(repairDto.descriptionText());
    }

    private void validateLocalDate(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("LocalDate cannot be null");
        }

        if (localDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("LocalDate cannot be in the past");
        }

        LocalDate latestAllowedDate = LocalDate.of(2100, 12, 31);

        if (localDate.isAfter(latestAllowedDate)) {
            throw new IllegalArgumentException("LocalDate must be before " + latestAllowedDate);
        }

        if (!localDate.isLeapYear() && localDate.getMonthValue() == 2 && localDate.getDayOfMonth() == 29) {
            throw new IllegalArgumentException("Invalid date for non-leap year");
        }

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("Date cannot fall on a weekend");
        }
    }

    private void validateShortDescription(String shortDescription) {
        if (shortDescription == null || shortDescription.length() < 3 || shortDescription.length() > 255) {
            throw new IllegalArgumentException("Short description must be between 3 and 255 characters");
        }

        if (shortDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Short description cannot be empty or consist only of whitespace");
        }

        if (!shortDescription.matches("^[a-zA-Z0-9\\s]+$")) {
            throw new IllegalArgumentException("Short description cannot contain special characters");
        }


    }

    private static void validateTypeOfRepair(TypeOfRepair typeOfRepair) {
        if (typeOfRepair == null || !EnumSet.allOf(TypeOfRepair.class).contains(typeOfRepair)) {
            throw new IllegalArgumentException("Invalid type of repair");
        }
    }

    private static void validateStatusOfRepair(StatusOfRepair statusOfRepair) {
        if (statusOfRepair == null || !EnumSet.allOf(StatusOfRepair.class).contains(statusOfRepair)) {
            throw new IllegalArgumentException("Invalid status of repair");
        }
    }

    private void validateCost(BigDecimal cost) {
        if (cost == null || cost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cost must be a positive non-zero value");
        }

        BigDecimal maxCost = new BigDecimal("1000000"); // Or any other maximum cost
        if (cost.compareTo(maxCost) > 0) {
            throw new IllegalArgumentException("Cost exceeds maximum allowed value");
        }

    }

    private void validateDescriptionText(String descriptionText) {
        if (descriptionText != null && descriptionText.length() > 1000) {
            throw new IllegalArgumentException("Description text length cannot exceed 1000 characters");
        }

        /*if (!descriptionText.matches("^[\p{L}0-9\\s.,!?]+$")) {
            throw new IllegalArgumentException("Description text cannot contain special characters");
        }*/

    }

}

