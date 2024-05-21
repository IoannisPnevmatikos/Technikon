package com.team1.technikon.service.impl;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.RepairReportDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.mapper.MapperTemp;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.repository.RepairRepository;
import com.team1.technikon.service.RepairService;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.team1.technikon.mapper.MapperTemp.*;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {

    private static final Logger logger = LoggerFactory.getLogger(RepairServiceImpl.class);

    private final RepairRepository repairRepository;

    /* IN EVERY METHOD I NEED TO FETCH THE OWNER AND OWNER PROPERTIES FIRST AND THEN IMPLEMENT THE METHODS
     * SINCE AN OWNER CANT SEARCH-DELETE ETC REPAIRS BY DATE OR ANY OTHER CRITERION OF OTHER OWNERS*/

    @Override
    @CachePut(value = "repairs", key = "#result.id")
    public RepairDto createRepair(RepairDto repairDto) throws EntityFailToCreateException, InvalidInputException {
        logger.info("Creating a repair: {}", repairDto);

        // VALIDATE ENTITY REPAIR ATTRIBUTES
        RepairValidator.validateCreateRepair(repairDto);

        try {
            Repair repair = mapToRepair(repairDto);
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

    @Cacheable("repairs")
    @Override
    public List<RepairDto> getRepairByDate(LocalDate localDate) throws EntityNotFoundException, InvalidInputException {

        // Validate input date
        RepairValidator.validateLocalDate(localDate);

        logger.info("Fetching repairs by date: {}", localDate);

        Repair repair = repairRepository.findByLocalDate(localDate)
                .orElseThrow(() -> {
                    logger.warn("No repair found for the given date: {}", localDate);
                    return new EntityNotFoundException("No repair found for the given date: " + localDate);
                });

        return Collections.singletonList(mapToRepairDto(repair));
    }

    @Override
    @Cacheable("repairs")
    public List<RepairDto> getRepairByRangeOfDates(LocalDate startingDate, LocalDate endingDate) throws EntityNotFoundException, InvalidInputException {

        // Validate input dates
        RepairValidator.validateDateRange(startingDate, endingDate);

        logger.info("Fetching repairs by date range: {} to {}", startingDate, endingDate);
        List<Repair> repairs = repairRepository.findByLocalDateBetween(startingDate, endingDate);
        if (repairs.isEmpty()) {
            logger.warn("No repairs found for the given range of dates: {} to {}", startingDate, endingDate);
            throw new EntityNotFoundException("No repairs found for the given range of dates: " + startingDate + " to " + endingDate);
        }
        return repairs.stream().map(MapperTemp::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable("repairs")
    public List<RepairDto> searchByOwnerTinNumber(String tinNumber) throws InvalidInputException, EntityNotFoundException {

        // Validate input
        RepairValidator.validateTinNumber(tinNumber);

        logger.info("Searching repairs by owner's TIN number: {}", tinNumber);
        List<Repair> repairs = repairRepository.findByPropertyOwnerTinNumber(tinNumber);
        if (repairs.isEmpty()) {
            logger.warn("No repairs found for the given owner's TIN number: {}", tinNumber);
            throw new EntityNotFoundException("No repairs found for the given owner's TIN number: " + tinNumber);
        }
        return repairs.stream().map(MapperTemp::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
    @CachePut(value = "repairs", key = "#id")
    public RepairDto updateRepair(long id, RepairDto repairDto) throws EntityNotFoundException, InvalidInputException {
        logger.info("Updating repair for repair ID {}", id);

        // VALIDATE ENTITY REPAIR ATTRIBUTES
        RepairValidator.validateCreateRepair(repairDto);
        RepairValidator.validateLocalDate(repairDto.localDate());

        // VALIDATE DATABASE
        repairRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Repair not found with ID: {}", id);
                    return new EntityNotFoundException("Repair not found with ID: " + id);
                });
        Repair repair;
        repair = mapToRepairNonNull(repairDto);
        repairRepository.save(repair);
        return mapToRepairDto(repair);


    }

    @Override
    @CacheEvict(value = "repairs", key = "#id")
    public void deleteRepair(long id) throws IllegalStateException, EntityNotFoundException {
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
            throw new IllegalStateException("Cannot delete repair with ID: " + id + ". It is not in PENDING status");
        }

        // Delete the repair
        repairRepository.delete(repair);
    }

    @Override
    @Cacheable("repairs")
    public List<RepairDto> getAllData() throws EntityNotFoundException {
        logger.info("Fetching all repairs");
        List<Repair> allRepairs = repairRepository.findAll();
        if (allRepairs.isEmpty()) {
            logger.warn("No repairs found!");
            throw new EntityNotFoundException("No repairs found!");
        }
        return allRepairs.stream()
                .map(MapperTemp::mapToRepairDto)
                .collect(Collectors.toList());
    }

    @Override
    /*The method calculates repair reports based on provided date ranges. These reports are dynamically generated and likely to
    change frequently. Caching data that is frequently updated defeats the purpose of caching, as it
    would require frequent cache invalidation.*/
    public List<RepairReportDto> getRepairReport(LocalDate startingDate, LocalDate endingDate) throws InvalidInputException, EntityNotFoundException {

        // Validate input dates
        RepairValidator.validateDateRange(startingDate, endingDate);

        List<Repair> repairs = repairRepository.findByLocalDateBetween(startingDate, endingDate);

        // Check if repairs are found
        if (repairs.isEmpty()) {
            throw new EntityNotFoundException("No repairs found for the specified date range.");
        }

        List<RepairReportDto> reportList = new ArrayList<>();

        BigDecimal pendingTotalCost = BigDecimal.ZERO;
        int pendingCount = 0;
        BigDecimal inProgressTotalCost = BigDecimal.ZERO;
        int inProgressCount = 0;
        BigDecimal completedTotalCost = BigDecimal.ZERO;
        int completedCount = 0;

        for (Repair repair : repairs) {
            StatusOfRepair status = repair.getStatusOfRepair();
            BigDecimal cost = repair.getCost();

            switch (status) {
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
                default:
                    break;
            }
        }

        reportList.add(new RepairReportDto(StatusOfRepair.PENDING, pendingTotalCost, pendingCount));
        reportList.add(new RepairReportDto(StatusOfRepair.IN_PROGRESS, inProgressTotalCost, inProgressCount));
        reportList.add(new RepairReportDto(StatusOfRepair.COMPLETE, completedTotalCost, completedCount));

        logger.info("Repair report for the period {} to {} : {}", startingDate, endingDate, reportList);

        return reportList;
    }
}
