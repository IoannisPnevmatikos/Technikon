package com.team1.technikon.service.impl;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.mapper.TechnikonMapper;
import com.team1.technikon.model.Property;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.service.PropertyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final TechnikonMapper technikonMapper;

    //CREATE
    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) throws InvalidInputException, EntityFailToCreateException {
//        Property property = new Property();
//        propertyRepository.save(property);
//        return new ResponseApi<>(0, "New property created!", property);
        if (isValidPropertyDto(propertyDto)) {
            try {
                log.info("Creating new property {}", propertyDto);
                return technikonMapper.toPropertyDto(propertyRepository.save(technikonMapper.toProperty(propertyDto)));
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
    public PropertyDto getPropertyById(String propertyId) throws EntityNotFoundException, InvalidInputException {
//        Property property = propertyRepository.findByPropertyId(propertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
//        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
//        return new ResponseApi<>(0, "Requested property found!", property);
        if (isValidE9(propertyId)){
            try {
                log.info("Getting property with E9 Number {}", propertyId);
                return propertyRepository.findByPropertyId(propertyId).map(technikonMapper::toPropertyDto).get();
            } catch (Exception e) {
                throw new EntityNotFoundException(e.getMessage());
            }
        }
        else {
            throw new InvalidInputException("Validation failed! Check user input again.");
        }
    }

    @Override
    public List<Property> getPropertyByOwnerTinNumber(String tinNumber) throws EntityNotFoundException {
//        List<Property> properties = propertyRepository.findByOwnerTinNumber(tinNumber);
//        List<Property> response = new ArrayList<>();
//        properties.forEach(property -> {
//            if (property.isActive()) response.add(property);
//        });
//        return response;
        try {
            log.info("Getting all properties from owner {}", tinNumber);
            return propertyRepository.findByOwnerTinNumber(tinNumber);
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Property> getPropertyByLocation() throws EntityNotFoundException {
//        List<Property> properties = propertyRepository.getPropertyByLocation();
//        List<Property> response = new ArrayList<>();
//        properties.forEach(property -> {
//            if (property.isActive()) response.add(property);
//        });
//        return response;
        try {
            log.info("Getting all properties in the area");
            return propertyRepository.getPropertyByLocation();
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
    public PropertyDto updateProperty(long id, PropertyDto propertyDto) throws EntityNotFoundException, InvalidInputException {
        Property property;
        property = propertyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Requested property not found."));
        if (!isValidPropertyDto(propertyDto)) throw new InvalidInputException("Requested property not found.");
        property = technikonMapper.toPropertyNoNull(propertyDto);
        propertyRepository.save(property);
        return technikonMapper.toPropertyDto(property);
    }

    //DELETE
    @Transactional
    @Override
    public boolean deleteProperty(long id) throws EntityNotFoundException {
//        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);;
//        if (property.getRepairs().isEmpty()) {
//            propertyRepository.deleteById(propertyId);
//            return new ResponseApi<>(0, "Property deleted successfully!", null);
//        } else {
//            property.setActive(false);
//            propertyRepository.save(property);
//            return new ResponseApi<>(0, "Property deactivated!", null);
//        }

        try {
            Optional<Property> property = propertyRepository.findById(id);
            if (property.isEmpty()) return false;
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


}
