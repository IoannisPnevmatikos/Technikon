package com.team1.technikon.service.impl;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.RepairReportDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.mapper.Mapper;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.repository.RepairRepository;
import com.team1.technikon.service.AdminRepairService;
import com.team1.technikon.validation.RepairValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.team1.technikon.mapper.Mapper.mapToRepair;
import static com.team1.technikon.mapper.Mapper.mapToRepairDto;

@Service
@AllArgsConstructor
public class AdminRepairServiceImpl implements AdminRepairService {
    private static final Logger logger = LoggerFactory.getLogger(AdminRepairServiceImpl.class);

    private final RepairRepository repairRepository;
    private final PropertyRepository propertyRepository;

    @Override
    public RepairDto createRepair(RepairDto repairDto) throws EntityFailToCreateException, InvalidInputException {
        logger.info("Creating a repair: {}", repairDto);

        // VALIDATE ENTITY REPAIR ATTRIBUTES
        RepairValidator.validateCreateRepair(repairDto);

        try {
            // Fetch the property by its ID
            Property property = propertyRepository.findByPropertyId(repairDto.propertyId())
                    .orElseThrow(() -> new InvalidInputException("Property not found"));

            Repair repair = mapToRepair(repairDto);
            repair.setProperty(property); // Set the resolved property

            // Save the repair entity
            repair = repairRepository.save(repair);

            return mapToRepairDto(repair);
        } catch (DataIntegrityViolationException e) {
            logger.error("Failed to create repair: {}", e.getMessage());
            throw new EntityFailToCreateException("Failed to create repair: Duplicate entry or invalid data.");
        } catch (Exception e) {
            logger.error("Failed to create repair: {}", e.getMessage());
            throw new EntityFailToCreateException("Failed to create repair: " + e.getMessage());
        }
    }

    @Override
    public List<RepairDto> getRepairByDate(LocalDate localDate) throws EntityNotFoundException, InvalidInputException {
        // Validate input date
        RepairValidator.validateLocalDate(localDate);

        logger.info("Fetching repairs by date: {}", localDate);

        List<Repair> repairs = repairRepository.findByLocalDate(localDate);
        if (repairs.isEmpty()) {
            logger.warn("No repair found for the given date: {}", localDate);
            throw new EntityNotFoundException("No repair found for the given date: " + localDate);
        }

        return repairs.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
    public List<RepairDto> getRepairByRangeOfDates(LocalDate startingDate, LocalDate endingDate) throws EntityNotFoundException, InvalidInputException {
        // Validate input dates
        RepairValidator.validateDateRange(startingDate, endingDate);

        logger.info("Fetching repairs by date range: {} to {}", startingDate, endingDate);
        List<Repair> repairs = repairRepository.findByLocalDateBetween(startingDate, endingDate);
        if (repairs.isEmpty()) {
            logger.warn("No repairs found for the given range of dates: {} to {}", startingDate, endingDate);
            throw new EntityNotFoundException("No repairs found for the given range of dates: " + startingDate + " to " + endingDate);
        }
        return repairs.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
    public List<RepairDto> searchByOwnerTinNumber(String tinNumber) throws InvalidInputException, EntityNotFoundException {
        // Validate input
        RepairValidator.validateTinNumber(tinNumber);

        logger.info("Searching repairs by owner's TIN number: {}",tinNumber);
        List<Repair> repairs = repairRepository.findByOwnerTinNumber(tinNumber);
        if (repairs.isEmpty()) {
            logger.warn("No repairs found for the given owner's TIN number: {}", tinNumber);
            throw new EntityNotFoundException("No repairs found for the given owner's TIN number: " + tinNumber);
        }
        return repairs.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
    public RepairDto updateRepair(long id, RepairDto repairDto) throws EntityNotFoundException, InvalidInputException {
        logger.info("Updating repair for repair ID {}", id);

        Repair repair = repairRepository.findById(id).orElseThrow(() -> {
            logger.warn("Repair not found with ID: {}", id);
            return new EntityNotFoundException("Requested repair not found");
        });

        // Update the repair attributes
        if(repairDto.localDate() != null) repair.setLocalDate(repairDto.localDate());
        if(repairDto.shortDescription() != null) repair.setShortDescription(repairDto.shortDescription());
        if(repairDto.typeOfRepair() != null) repair.setTypeOfRepair(repairDto.typeOfRepair());
        if(repairDto.statusOfRepair() != null) repair.setStatusOfRepair(repairDto.statusOfRepair());
        if(repairDto.cost() != null) repair.setCost(repairDto.cost());
        if(repairDto.descriptionText() != null) repair.setDescriptionText(repairDto.descriptionText());
        if(repairDto.propertyId() != null) {
            Property property = propertyRepository.findByPropertyId(repairDto.propertyId())
                    .orElseThrow(() -> new InvalidInputException("Property not found"));
            repair.setProperty(property);
        }

        // VALIDATE ENTITY REPAIR ATTRIBUTES
        RepairValidator.validateCreateRepair(repairDto);
        RepairValidator.validateLocalDate(repairDto.localDate());

        repairRepository.save(repair);
        return mapToRepairDto(repair);
    }

    @Override
    public void deleteRepair(long id) throws EntityNotFoundException, UnauthorizedAccessException {
        logger.info("Deleting repair with ID: {}", id);

        // Check if the repair exists
        Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new EntityNotFoundException("Repair not found with ID: " + id);
                });

        // Check if the repair is in PENDING status
        if (!repair.getStatusOfRepair().equals(StatusOfRepair.PENDING)) {
            logger.error("Cannot delete repair with ID: {}. It is not in PENDING status", id);
            throw new UnauthorizedAccessException("Cannot delete repair with ID: " + id + ". It is not in PENDING status");
        }

        // Delete the repair
        repairRepository.deleteById(id);
    }

    @Override
    public List<RepairDto> getAllData() throws EntityNotFoundException {
        logger.info("Fetching all repairs");
        List<Repair> allRepairs = repairRepository.findAll();
        if (allRepairs.isEmpty()) {
            logger.warn("No repairs found!");
            throw new EntityNotFoundException("No repairs found!");
        }
        return allRepairs.stream()
                .map(Mapper::mapToRepairDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairReportDto> getRepairReport(LocalDate startingDate, LocalDate endingDate) throws InvalidInputException, EntityNotFoundException {
        // Validate input dates
        RepairValidator.validateDateRange(startingDate, endingDate);

        // Fetch repairs within the specified date range
        List<Repair> repairs = repairRepository.findByLocalDateBetween(startingDate, endingDate);

        // Check if any repairs are found
        if (repairs.isEmpty()) {
            throw new EntityNotFoundException("No repairs found for the specified date range.");
        }

        // Initialize the report list and counters
        List<RepairReportDto> reportList = new ArrayList<>();

        BigDecimal scheduledTotalCost = BigDecimal.ZERO;
        int scheduledCount = 0;
        BigDecimal pendingTotalCost = BigDecimal.ZERO;
        int pendingCount = 0;
        BigDecimal inProgressTotalCost = BigDecimal.ZERO;
        int inProgressCount = 0;
        BigDecimal completedTotalCost = BigDecimal.ZERO;
        int completedCount = 0;

        // Aggregate costs and counts based on repair status
        for (Repair repair : repairs) {
            StatusOfRepair status = repair.getStatusOfRepair();
            BigDecimal cost = repair.getCost();

            switch (status) {
                case SCHEDULED:
                    scheduledTotalCost = scheduledTotalCost.add(cost);
                    scheduledCount++;
                    break;
                case PENDING:
                    pendingTotalCost = pendingTotalCost.add(cost);
                    pendingCount++;
                    break;
                case IN_PROGRESS:
                    inProgressTotalCost = inProgressTotalCost.add(cost);
                    inProgressCount++;
                    break;
                case COMPLETE:
                    completedTotalCost = completedTotalCost.add(cost);
                    completedCount++;
                    break;
            }
        }

        // Create report entries
        reportList.add(new RepairReportDto(StatusOfRepair.SCHEDULED, scheduledTotalCost, scheduledCount));
        reportList.add(new RepairReportDto(StatusOfRepair.PENDING, pendingTotalCost, pendingCount));
        reportList.add(new RepairReportDto(StatusOfRepair.IN_PROGRESS, inProgressTotalCost, inProgressCount));
        reportList.add(new RepairReportDto(StatusOfRepair.COMPLETE, completedTotalCost, completedCount));

        // Log the report details
        logger.info("Repair report for the period {} to {} : {}", startingDate, endingDate, reportList);

        return reportList;
    }
}
