package com.team1.technikon.service.impl;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.service.PropertyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static com.team1.technikon.mapper.Mapper.*;
import static com.team1.technikon.validation.PropertyValidator.isValidE9;
import static com.team1.technikon.validation.PropertyValidator.isValidPropertyDto;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;

    //CREATE
    @Override
    public PropertyDto createProperty(long id, PropertyDto propertyDto) throws InvalidInputException, EntityFailToCreateException, EntityNotFoundException {
        isValidPropertyDto(propertyDto);
        log.info("Creating new property {}", propertyDto);
        Property property = mapToProperty(propertyDto);
        property.setOwner(ownerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Owner not found.")));
        try {
            return mapToPropertyDto(propertyRepository.save(property));
        } catch (Exception e) {
            throw new EntityFailToCreateException(e.getMessage());
        }
    }

    //SEARCH
    @Override
    public PropertyDto getPropertyById(Long ownerId, String propertyId) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException {
        isValidE9(propertyId);
        log.info("Getting property with E9 Number {}", propertyId);
        Property property = propertyRepository.findByPropertyId(propertyId).orElseThrow(() -> new EntityNotFoundException("Property not found."));
        if (!(ownerId == null || ownerId == property.getOwner().getId())) throw new UnauthorizedAccessException("You are unable to retrieve this data.");
        return mapToPropertyDto(property);
    }

    @Override
    public List<Property> getPropertyByOwnerTinNumber(Long ownerId, String tinNumber) throws EntityNotFoundException {
        log.info("Getting all properties from requested owner");
        List<Property> properties;
        if (tinNumber!=null) {
            properties = ownerRepository.findByTinNumber(tinNumber).orElseThrow(() -> new EntityNotFoundException("Requested tinNumber does not exist.")).getProperties();
        }
        else {
            properties = ownerRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException("Requested tinNumber does not exist.")).getProperties();
        }
        if (properties.isEmpty()) throw new EntityNotFoundException("No properties found for the requested user.");
        return properties;
    }

    @Override
    public List<Property> getPropertyByLocation(Long ownerId) {
        return null;
    }

    @Override
    public List<Property> getAllData() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> getPropertyByRangeOfDates(LocalDate startDate, LocalDate endDate) throws EntityNotFoundException {
        log.info("Fetching properties by registration date range: {} to {}", startDate, endDate);
        List<Property> properties = propertyRepository.findByRegistrationDateBetween(startDate, endDate);
        if (properties.isEmpty()) throw new EntityNotFoundException("No properties found for the requested date range.");
        return properties;
    }

    //UPDATE
    @Transactional
    @Override
    public PropertyDto updateProperty(Long ownerId, String propertyId, PropertyDto propertyDto) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException, EntityFailToCreateException {
        Property property = propertyRepository.findByPropertyId(propertyId).orElseThrow(() -> new EntityNotFoundException("Requested property not found."));
        if (ownerId!=null && property.getOwner().getId()!=ownerId) throw new UnauthorizedAccessException("You are unable to modify this entity.");
        if (propertyDto.propertyId()!=null) property.setPropertyId(propertyDto.propertyId());
        if (propertyDto.address()!=null) property.setAddress(propertyDto.address());
        if (propertyDto.yearOfConstruction()!=null) property.setYearOfConstruction(propertyDto.yearOfConstruction());
        if (propertyDto.typeOfProperty()!=null) property.setTypeOfProperty(propertyDto.typeOfProperty());
        if (propertyDto.photo()!=null) property.setPhoto(propertyDto.photo());
        if (propertyDto.mapLocation()!=null) {
            MapLocation mapLocation = new MapLocation();
            if (propertyDto.mapLocation().getLatitude()!=null) mapLocation.setLatitude(propertyDto.mapLocation().getLatitude());
            else mapLocation.setLatitude(property.getMapLocation().getLatitude());
            if (propertyDto.mapLocation().getLongitude()!=null) mapLocation.setLongitude(propertyDto.mapLocation().getLongitude());
            else mapLocation.setLongitude(property.getMapLocation().getLongitude());
            property.setMapLocation(mapLocation);
        }
        isValidPropertyDto(mapToPropertyDto(property));
        try {
            propertyRepository.save(property);
        } catch (Exception e) {
            throw new EntityFailToCreateException("E9 number for the property conflicts with an already existing property.");
        }
        return mapToPropertyDto(property);
    }

    //DELETE
    @Transactional
    @Override
    public boolean deleteProperty(Long ownerId, String propertyId) throws EntityNotFoundException, UnauthorizedAccessException {

        Property property = propertyRepository.findByPropertyId(propertyId).orElseThrow(() -> new EntityNotFoundException("Requested property not found."));
        if (ownerId!=null && property.getOwner().getId()!=ownerId) throw new UnauthorizedAccessException("You are unable to delete this entity.");
        if (property.getRepairs().isEmpty())
        {
            propertyRepository.deleteById(property.getId());
        } else {
            property.setActive(false);
            propertyRepository.save(property);
        }
        return true;
    }
}