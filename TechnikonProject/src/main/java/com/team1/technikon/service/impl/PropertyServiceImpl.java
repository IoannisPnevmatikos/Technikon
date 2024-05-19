package com.team1.technikon.service.impl;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.mapper.TechnikonMapper;
import com.team1.technikon.model.Property;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.service.OwnerService;
import com.team1.technikon.service.PropertyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
//    private final TechnikonMapper technikonMapper;
    private final OwnerRepository ownerRepository;

    //CREATE
    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) throws InvalidInputException, EntityFailToCreateException {
        if (true){//isValidPropertyDto(propertyDto)) {
            try {
                log.info("Creating new property {}", propertyDto);
                return mapToPropertyDto(propertyRepository.save(mapToProperty(propertyDto)));
            } catch (Exception e) {
                throw new EntityFailToCreateException(e.getMessage());
            }
        }
        else {
            throw new InvalidInputException("Validation failed! Check user input again.");
        }
    }

    //SEARCH
    @Override
    public PropertyDto getPropertyById(Long ownerId, String propertyId) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException {
        if (isValidE9(propertyId)){
            try {
                log.info("Getting property with E9 Number {}", propertyId);
                PropertyDto propertyDto = mapToPropertyDto(propertyRepository.findByPropertyId(propertyId).get());
                if (ownerId == null || ownerId == propertyDto.owner().getId()) return propertyDto;
                else throw new UnauthorizedAccessException("You are unable to retrieve this data");
            } catch (Exception e) {
                throw new EntityNotFoundException(e.getMessage());
            }
        }
        else throw new InvalidInputException("Validation failed! Check user input again.");
    }

    @Override
    public List<Property> getPropertyByOwnerTinNumber(Long ownerId, String tinNumber) throws EntityNotFoundException, UnauthorizedAccessException {
//        List<Property> properties = propertyRepository.findByOwnerTinNumber(tinNumber);
//        properties.forEach(property -> {
//            if (property.isActive()) response.add(property);
//        });
//        return response;
        String ownerTin = ownerRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException("Requested property not found.")).getTinNumber();
        if (ownerId != null && !tinNumber.matches(ownerTin)) throw new UnauthorizedAccessException("You are unable to retrieve this data");
        try {
            log.info("Getting all properties from owner {}", tinNumber);
            return propertyRepository.findByOwnerTinNumber(tinNumber);
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Property> getPropertyByLocation(Long ownerId) throws EntityNotFoundException {
//        List<Property> properties = propertyRepository.getPropertyByLocation();
//        List<Property> response = new ArrayList<>();
//        properties.forEach(property -> {
//            if (property.isActive()) response.add(property);
//        });
//        return response;
        try {
            log.info("Getting all properties in the area");
            List<Property> initialResponse = new ArrayList<>();
            List<Property> finalResponse = new ArrayList<>();
            initialResponse = propertyRepository.getPropertyByLocation();
            initialResponse.forEach(property -> {
                if (ownerId == null || property.getOwner().getId() == ownerId) {
                    finalResponse.add(property);
                }
            });
            return finalResponse;
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Property> getAllData() {
        return propertyRepository.findAll();
    }

    //UPDATE
    @Transactional
    @Override
    public PropertyDto updateProperty(Long ownerId, long id, PropertyDto propertyDto) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException {
        Property property;
        property = propertyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Requested property not found."));
        if (property.getOwner().getId()!=ownerId) throw new UnauthorizedAccessException("You are unable to modify this entity");
        if (!isValidPropertyDto(propertyDto)) throw new InvalidInputException("Validation failed! Check user input again.");
        property = mapToPropertyNoNull(propertyDto);
        propertyRepository.save(property);
        return mapToPropertyDto(property);
    }

    //DELETE
    @Transactional
    @Override
    public boolean deleteProperty(Long ownerId, long id) throws EntityNotFoundException {
        try {
            Optional<Property> property = propertyRepository.findById(id);
            if (property.isEmpty()) return false;
            if (ownerId != null && property.get().getOwner().getId() != ownerId) throw new UnauthorizedAccessException("You are unable to delete this entity");
            if (property.get().getRepairs().isEmpty())
            {
                propertyRepository.deleteById(id);
            } else {
                property.get().setActive(false);
                propertyRepository.save(property.get());
            }
            return true;
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    private boolean isValidPropertyDto(PropertyDto propertyDto) {
        return isValidE9(propertyDto.propertyId()) && isValidYearOfConstruction(propertyDto.yearOfConstruction());
    }

    private boolean isValidE9(String propertyId) {
        return propertyId.matches("\\d{11}");
    }

    private boolean isValidYearOfConstruction(String yearOfConstruction) {
        int minRange = 1800; // Minimum range value
        int maxRange = LocalDate.now().getYear(); // Maximum range value
        String regex = String.format("\\b([1-9]%d|[1-9]\\d{2}|9[0-%d]\\d|9%d)\\b", minRange / 1000, (maxRange / 1000) % 10, (maxRange % 1000) / 100);
        return (yearOfConstruction.matches(regex));
    }

    private PropertyDto mapToPropertyDto(Property property) {
        return new PropertyDto(
                property.getPropertyId(),
                property.getAddress(),
                property.getYearOfConstruction(),
                property.getTypeOfProperty(),
                property.getPhoto(),
                property.getMapLocation(),
                property.getOwner()
        );
    }

    private Property mapToProperty(PropertyDto propertyDto) {
        Property property = new Property();
        property.setPropertyId(propertyDto.propertyId());
        property.setAddress(propertyDto.address());
        property.setYearOfConstruction(propertyDto.yearOfConstruction());
        property.setTypeOfProperty(propertyDto.typeOfProperty());
        property.setPhoto(propertyDto.photo());
        property.setMapLocation(propertyDto.mapLocation());
        property.setOwner(propertyDto.owner());
        return property;
    }

    private Property mapToPropertyNoNull(PropertyDto propertyDto) {
        Property property = new Property();
        if (propertyDto.propertyId()!=null) property.setPropertyId(propertyDto.propertyId());
        if (propertyDto.address()!=null) property.setAddress(propertyDto.address());
        if (propertyDto.yearOfConstruction()!=null) property.setYearOfConstruction(propertyDto.yearOfConstruction());
        if (propertyDto.typeOfProperty()!=null) property.setTypeOfProperty(propertyDto.typeOfProperty());
        if (propertyDto.photo()!=null) property.setPhoto(propertyDto.photo());
        if (propertyDto.mapLocation()!=null) property.setMapLocation(propertyDto.mapLocation());
        if (propertyDto.owner()!=null) property.setOwner(propertyDto.owner());
        return property;
    }

}

//    @Override
//    public ResponseApi<Property> updatePropertyId(long currentPropertyId, long newPropertyId) {
//        Property property = propertyRepository.findById(currentPropertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
//        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
//        propertyRepository.updatePropertyId(currentPropertyId, newPropertyId);
//        property.setPropertyId(newPropertyId);
//        return new ResponseApi<>(0, "Property updated successfully!", property);
//    }
//
//    @Override
//    public ResponseApi<Property> updateAddress(long propertyId, String address) {
//        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
//        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
//        propertyRepository.updateAddress(propertyId, address);
//        property.setAddress(address);
//        return new ResponseApi<>(0, "Property updated successfully!", property);
//    }
//
//    @Override
//    public ResponseApi<Property> updateYearOfConstruction(long propertyId, String yearOfConstruction) {
//        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
//        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
//        propertyRepository.updateYearOfConstruction(propertyId, yearOfConstruction);
//        property.setYearOfConstruction(yearOfConstruction);
//        return new ResponseApi<>(0, "Property updated successfully!", property);
//    }
//
//    @Override
//    public ResponseApi<Property> updatePropertyType(long propertyId, TypeOfProperty typeOfProperty) {
//        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
//        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
//        propertyRepository.updatePropertyType(propertyId, typeOfProperty);
//        property.setTypeOfProperty(typeOfProperty);
//        return new ResponseApi<>(0, "Property updated successfully!", property);
//    }
//
//    @Override
//    public ResponseApi<Property> updatePhoto(long propertyId, String photo) {
//        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
//        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
//        propertyRepository.updatePhoto(propertyId, photo);
//        property.setPhoto(photo);
//        return new ResponseApi<>(0, "Property updated successfully!", property);
//    }
//
//    @Override
//    public ResponseApi<Property> updateMapLocation(long propertyId, MapLocation mapLocation) {
//        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
//        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
//        propertyRepository.updateMapLocation(propertyId, mapLocation);
//        property.setMapLocation(mapLocation);
//        return new ResponseApi<>(0, "Property updated successfully!", property);
//    }
