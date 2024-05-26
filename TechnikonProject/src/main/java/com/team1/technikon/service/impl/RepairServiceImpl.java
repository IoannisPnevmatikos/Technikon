package com.team1.technikon.service.impl;

import com.team1.technikon.dto.RepairDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.mapper.Mapper;
import com.team1.technikon.model.Owner;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.StatusOfRepair;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.repository.RepairRepository;
import com.team1.technikon.service.RepairService;
import com.team1.technikon.validation.PropertyValidator;
import com.team1.technikon.validation.RepairValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {

    private static final Logger logger = LoggerFactory.getLogger(RepairServiceImpl.class);

    private final RepairRepository repairRepository;
    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;

    @Override
    public RepairDto createRepair(long ownerId, RepairDto repairDto) throws EntityFailToCreateException, InvalidInputException {
        logger.info("Creating a repair for owner ID {}: {}", ownerId, repairDto);

        // Validate entity repair attributes
        RepairValidator.validateCreateRepair(repairDto);
        PropertyValidator.isValidE9(repairDto.propertyId());

        try {
            // Fetch the owner entity using the ownerId
            Owner owner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new InvalidInputException("Owner with ID " + ownerId + " not found."));

            // Fetch the property from the repository to ensure it's managed by the persistence context
            Property managedProperty = propertyRepository.findByPropertyId(repairDto.propertyId())
                    .orElseThrow(() -> new InvalidInputException("Property with ID " + repairDto.propertyId() + " not found."));

            // Ensure the property belongs to the owner
            if (managedProperty.getOwner().getId() != ownerId) {
                throw new InvalidInputException("Property with ID " + repairDto.propertyId() + " does not belong to owner with ID " + ownerId);
            }

            // Map RepairDto to Repair entity
            Repair repair = Mapper.mapToRepair(repairDto);
            // Associate the managed property with the repair
            repair.setProperty(managedProperty);

            // Save the repair entity
            repairRepository.save(repair);

            return Mapper.mapToRepairDto(repair);
        } catch (DataIntegrityViolationException e) {
            logger.error("Failed to create repair: {}", e.getMessage());
            throw new EntityFailToCreateException("Failed to create repair: Duplicate entry or invalid data.");
        } catch (Exception e) {
            logger.error("Failed to create repair: {}", e.getMessage());
            throw new EntityFailToCreateException("Failed to create repair: " + e.getMessage());
        }
    }

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
                .filter(repair -> repair.getProperty().getOwner().getId() == ownerId)
                .collect(Collectors.toList());


        if (ownerRepairs.isEmpty()) {
            logger.warn("No repairs found for the given date: {} and owner ID: {}", localDate, ownerId);
            throw new EntityNotFoundException("No repairs found for the given date: " + localDate + " and owner ID: " + ownerId);
        }

        // Map each Repair entity to RepairDto and return the list
        return ownerRepairs.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
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
                .filter(repair -> repair.getProperty().getOwner().getId() == ownerId)
                .collect(Collectors.toList());


        if (ownerRepairsInRange.isEmpty()) {
            logger.warn("No repairs found for owner ID {} in the given range of dates: {} to {}", ownerId, startingDate, endingDate);
            throw new EntityNotFoundException("No repairs found for owner ID " + ownerId + " in the given range of dates: " + startingDate + " to " + endingDate);
        }

        // Map each Repair entity to RepairDto and return the list
        return ownerRepairsInRange.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
    public List<RepairDto> searchByOwnerId(long id) throws EntityNotFoundException {

        logger.info("Fetching repairs for owner ID: {}", id);

        // Fetch the owner entity using the ownerId
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner with ID " + id + " not found."));

        // Fetch repairs associated with the owner's properties
        List<Repair> ownerRepairs = repairRepository.findByOwnerId(id);

        if (ownerRepairs.isEmpty()) {
            logger.warn("No repairs found for owner ID: {}", id);
            throw new EntityNotFoundException("No repairs found for owner ID: " + id);
        }

        // Map each Repair entity to RepairDto and return the list
        return ownerRepairs.stream().map(Mapper::mapToRepairDto).collect(Collectors.toList());
    }

    @Override
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
        if (repairDto.localDate() != null) repair.setLocalDate(repairDto.localDate());
        if (repairDto.shortDescription() != null) repair.setShortDescription(repairDto.shortDescription());
        if (repairDto.typeOfRepair() != null) repair.setTypeOfRepair(repairDto.typeOfRepair());
        if (repairDto.statusOfRepair() != null) repair.setStatusOfRepair(repairDto.statusOfRepair());
        if (repairDto.cost() != null) repair.setCost(repairDto.cost());
        if (repairDto.descriptionText() != null) repair.setDescriptionText(repairDto.descriptionText());

        // Validate updated repair attributes
        RepairValidator.validateCreateRepair(repairDto);
        RepairValidator.validateLocalDateAtCreate(repairDto.localDate());

        // Save the updated repair
        repairRepository.save(repair);

        return Mapper.mapToRepairDto(repair);
    }

    @Override
    public void deleteRepair(long ownerId, long id) throws EntityNotFoundException, UnauthorizedAccessException {
        logger.info("Deleting repair with ID: {} for owner ID: {}", id, ownerId);

        // Fetch the repair entity by ID
        Repair repair = repairRepository.findById(id).orElseThrow(() -> {
            logger.warn("Repair not found with ID: {}", id);
            return new EntityNotFoundException("Repair not found with ID: " + id);
        });

        // Ensure that the repair belongs to the specified owner
        if (repair.getProperty().getOwner().getId() != ownerId) {
            throw new UnauthorizedAccessException("Cannot delete repair with ID: " + id + ". It does not belong to the specified owner.");
        }


        // Check if the repair is in PENDING status
        if (!repair.getStatusOfRepair().equals(StatusOfRepair.PENDING)) {
            logger.error("Cannot delete repair with ID: {}. It is not in PENDING status", id);
            throw new UnauthorizedAccessException("Cannot delete repair with ID: " + id + ". It is not in PENDING status");
        }

        // Delete the repair
        repairRepository.deleteById(id);
    }
}
