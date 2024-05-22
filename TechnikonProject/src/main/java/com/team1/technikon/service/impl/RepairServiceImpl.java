package com.team1.technikon.service.impl;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.dto.RepairReportDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.mapper.Mapper;
import com.team1.technikon.model.Owner;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.repository.PropertyRepository;
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

import static com.team1.technikon.mapper.Mapper.*;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {

    private static final Logger logger = LoggerFactory.getLogger(RepairServiceImpl.class);

    private final RepairRepository repairRepository;
    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;

    @Override
    @CachePut(value = "repairs", key = "#result.id")
    public RepairDto createRepair(long ownerId,RepairDto repairDto) throws EntityFailToCreateException, InvalidInputException {
        logger.info("Creating a repair for owner ID {}: {}", ownerId, repairDto);

        // VALIDATE ENTITY REPAIR ATTRIBUTES
        RepairValidator.validateCreateRepair(repairDto);

        try {
            // Fetch the owner entity using the ownerId
            Owner owner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new InvalidInputException("Owner with ID " + ownerId + " not found."));

            // Validate the property in the repairDto
            Property property = repairDto.property();
            if (property == null) {
                throw new InvalidInputException("Property must be provided.");
            }

            // Fetch the property from the repository to ensure it's managed by the persistence context
            Property managedProperty = propertyRepository.findById(property.getId())
                    .orElseThrow(() -> new InvalidInputException("Property with ID " + property.getId() + " not found."));

            // Ensure the property belongs to the owner
            if (!Long.valueOf(managedProperty.getOwner().getId()).equals(ownerId)) {
                throw new InvalidInputException("Property with ID " + property.getId() + " does not belong to owner with ID " + ownerId);
            }

            Repair repair = mapToRepair(repairDto);
            // Associate the managed property with the repair
            repair.setProperty(managedProperty);

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
    public List<RepairDto> getRepairByDate(long ownerId, LocalDate localDate) throws EntityNotFoundException, InvalidInputException {

        // Validate input date
        RepairValidator.validateLocalDate(localDate);

        logger.info("Fetching repairs for owner ID {} by date: {}", ownerId, localDate);

        // Fetch the owner entity using the ownerId
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new InvalidInputException("Owner with ID " + ownerId + " not found."));

        // Fetch repairs by the specified date
        List<Repair> repairs = repairRepository.findByLocalDate(localDate);

        // Filter repairs by the owner's properties
        List<Repair> ownerRepairs = repairs.stream()
                .filter(repair -> Long.valueOf(repair.getProperty().getOwner().getId()).equals(ownerId))
                .collect(Collectors.toList());

        if (ownerRepairs.isEmpty()) {
            logger.warn("No repairs found for the given date: {} and owner ID: {}", localDate, ownerId);
            throw new EntityNotFoundException("No repairs found for the given date: " + localDate + " and owner ID: " + ownerId);
        }

        // Map each Repair entity to RepairDto and return the list
        return ownerRepairs.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable("repairs")
    public List<RepairDto> getRepairByRangeOfDates(long ownerId, LocalDate startingDate, LocalDate endingDate) throws EntityNotFoundException, InvalidInputException {

        // Validate input dates
        RepairValidator.validateDateRange(startingDate, endingDate);

        logger.info("Fetching repairs for owner ID {} by date range: {} to {}", ownerId, startingDate, endingDate);

        // Fetch the owner entity using the ownerId
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new InvalidInputException("Owner with ID " + ownerId + " not found."));

        // Fetch repairs within the date range
        List<Repair> repairsInRange = repairRepository.findByLocalDateBetween(startingDate, endingDate);

        // Filter repairs by the owner's properties
        List<Repair> ownerRepairsInRange = repairsInRange.stream()
                .filter(repair -> Long.valueOf(repair.getProperty().getOwner().getId()).equals(ownerId))
                .collect(Collectors.toList());

        if (ownerRepairsInRange.isEmpty()) {
            logger.warn("No repairs found for owner ID {} in the given range of dates: {} to {}", ownerId, startingDate, endingDate);
            throw new EntityNotFoundException("No repairs found for owner ID " + ownerId + " in the given range of dates: " + startingDate + " to " + endingDate);
        }

        // Map each Repair entity to RepairDto and return the list
        return ownerRepairsInRange.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
    @Cacheable("repairs")
    public List<RepairDto> searchByOwnerId(long ownerId) throws EntityNotFoundException {

        logger.info("Fetching repairs for owner ID: {}", ownerId);

        // Fetch the owner entity using the ownerId
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner with ID " + ownerId + " not found."));

        // Fetch repairs associated with the owner's properties
        List<Repair> ownerRepairs = repairRepository.findByOwnerId(ownerId);

        if (ownerRepairs.isEmpty()) {
            logger.warn("No repairs found for owner ID: {}", ownerId);
            throw new EntityNotFoundException("No repairs found for owner ID: " + ownerId);
        }

        // Map each Repair entity to RepairDto and return the list
        return ownerRepairs.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }


    @Override
    @CachePut(value = "repairs", key = "#id")
    public RepairDto updateRepair(long ownerId, long id, RepairDto repairDto) throws EntityNotFoundException, InvalidInputException {
        logger.info("Updating repair for repair ID {} and owner ID {}", id, ownerId);

        // Fetch the repair entity by ID
        Repair repair = repairRepository.findById(id).orElseThrow(() -> {
            logger.warn("Repair not found with ID: {}", id);
            return new EntityNotFoundException("Requested repair not found");
        });

        // Ensure that the repair belongs to the specified owner
        if (repair.getProperty().getOwner().getId() != ownerId) {
            throw new InvalidInputException("Repair with ID " + id + " does not belong to the specified owner.");
        }

        // Update the repair attributes
        if (repair.getLocalDate() != null) repair.setLocalDate(repairDto.localDate());
        if (repair.getShortDescription() != null) repair.setShortDescription(repairDto.shortDescription());
        if (repair.getTypeOfRepair() != null) repair.setTypeOfRepair(repairDto.typeOfRepair());
        if (repair.getStatusOfRepair() != null) repair.setStatusOfRepair(repairDto.statusOfRepair());
        if (repair.getCost() != null) repair.setCost(repairDto.cost());
        if (repair.getDescriptionText() != null) repair.setDescriptionText(repairDto.descriptionText());

        // Validate updated repair attributes
        RepairValidator.validateCreateRepair(repairDto);
        RepairValidator.validateLocalDate(repairDto.localDate());

        // Save the updated repair
        repairRepository.save(repair);

        return mapToRepairDto(repair);
    }


    @Override
    @CacheEvict(value = "repairs", key = "#id")
    public void deleteRepair(long ownerId,long id) throws IllegalStateException, EntityNotFoundException {
        logger.info("Deleting repair with ID: {} for owner ID: {}", id, ownerId);

        // Fetch the repair entity by ID
        Repair repair = repairRepository.findById(id).orElseThrow(() -> {
            logger.warn("Repair not found with ID: {}", id);
            return new EntityNotFoundException("Repair not found with ID: " + id);
        });

        // Ensure that the repair belongs to the specified owner
        if (repair.getProperty().getOwner().getId() != ownerId) {
            throw new IllegalStateException("Cannot delete repair with ID: " + id + ". It does not belong to the specified owner.");
        }

        // Check if the repair is in PENDING status
        if (!repair.getStatusOfRepair().equals(StatusOfRepair.PENDING)) {
            logger.error("Cannot delete repair with ID: {}. It is not in PENDING status", id);
            throw new IllegalStateException("Cannot delete repair with ID: " + id + ". It is not in PENDING status");
        }

        // Delete the repair
        repairRepository.delete(repair);
    }


    /*@Override
    @Cacheable("repairs")
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
    }*/

    //@Override
    /*The method calculates repair reports based on provided date ranges. These reports are dynamically generated and likely to
    change frequently. Caching data that is frequently updated defeats the purpose of caching, as it
    would require frequent cache invalidation.*/
    /*public List<RepairReportDto> getRepairReport(LocalDate startingDate, LocalDate endingDate) throws InvalidInputException, EntityNotFoundException {

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
    }*/
}
