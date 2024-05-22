package com.team1.technikon.service.impl;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
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

import java.util.List;
import java.util.Optional;

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
            return mapToPropertyDto(propertyRepository.save(mapToProperty(propertyDto)));
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
    public List<Property> getPropertyByOwnerTinNumber(Long ownerId, String tinNumber) throws EntityNotFoundException, UnauthorizedAccessException {
        log.info("Getting all properties from owner {}", tinNumber);
        String ownerTin = ownerRepository.findByTinNumber(tinNumber).orElseThrow(() -> new EntityNotFoundException("Requested tinNumber does not exist.")).getTinNumber();
        if (ownerId != null && !tinNumber.matches(ownerTin)) throw new UnauthorizedAccessException("You are unable to retrieve this data.");
        List<Property> properties = propertyRepository.findByOwnerTinNumber(tinNumber);
        if (properties.isEmpty()) throw new EntityNotFoundException("No properties found for the requested user.");
        return properties;
    }

    @Override
    public List<Property> getPropertyByLocation(Long ownerId) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<Property> getAllData() {
        return propertyRepository.findAll();
    }

    //UPDATE
    @Transactional
    @Override
    public PropertyDto updateProperty(Long ownerId, long id, PropertyDto propertyDto) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Requested property not found."));
        if (ownerId!=null && property.getOwner().getId()!=ownerId) throw new UnauthorizedAccessException("You are unable to modify this entity.");
        if (propertyDto.propertyId()!=null) property.setPropertyId(propertyDto.propertyId());
        if (propertyDto.address()!=null) property.setAddress(propertyDto.address());
        if (propertyDto.yearOfConstruction()!=null) property.setYearOfConstruction(propertyDto.yearOfConstruction());
        if (propertyDto.typeOfProperty()!=null) property.setTypeOfProperty(propertyDto.typeOfProperty());
        if (propertyDto.photo()!=null) property.setPhoto(propertyDto.photo());
        if (propertyDto.mapLocation()!=null) property.setMapLocation(propertyDto.mapLocation());
        isValidPropertyDto(mapToPropertyDto(property));
        propertyRepository.save(property);
        return mapToPropertyDto(property);
    }

    //DELETE
    @Transactional
    @Override
    public boolean deleteProperty(Long ownerId, long id) throws EntityNotFoundException, UnauthorizedAccessException {

        Property property = propertyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Requested property not found."));
        if (ownerId!=null && property.getOwner().getId()!=ownerId) throw new UnauthorizedAccessException("You are unable to delete this entity.");
        if (property.getRepairs().isEmpty())
        {
            propertyRepository.deleteById(id);
        } else {
            property.setActive(false);
            propertyRepository.save(property);
        }
        return true;
    }
}