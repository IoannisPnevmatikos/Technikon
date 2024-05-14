package com.team1.technikon.service.impl;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.mapper.TechnikonMapper;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Owner;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.enums.TypeOfProperty;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.service.PropertyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public PropertyDto createProperty(PropertyDto propertyDto) throws OwnerFailToCreateException {
//        Property property = new Property();
//        propertyRepository.save(property);
//        return new ResponseApi<>(0, "New property created!", property);
        try {
            log.info("Creating new property {}", propertyDto);
            return technikonMapper.toPropertyDto(propertyRepository.save(technikonMapper.toProperty(propertyDto)));
        } catch (Exception e) {
            throw new OwnerFailToCreateException(e.getMessage());
        }
    }

    //SEARCH
    @Override
    public PropertyDto getPropertyById(String propertyId) throws OwnerNotFoundException {
//        Property property = propertyRepository.findByPropertyId(propertyId).orElseThrow(null);
//        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
//        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
//        return new ResponseApi<>(0, "Requested property found!", property);
        try {
            log.info("Getting property with E9 Number {}", propertyId);
            return propertyRepository.findByPropertyId(propertyId).map(technikonMapper::toPropertyDto).get();
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Property> getPropertyByOwnerTinNumber(String tinNumber) throws OwnerNotFoundException {
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
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Property> getPropertyByLocation() throws OwnerNotFoundException {
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
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Property> getAllData() {
        return propertyRepository.findAll();
    }

    //UPDATE
    @Transactional
    @Override
    public PropertyDto updateProperty(long id, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(id).orElse(null);
        if (property != null) return null;
        property = technikonMapper.toPropertyNoNull(propertyDto);
        propertyRepository.save(property);
        return technikonMapper.toPropertyDto(property);
    }

    //DELETE
    @Transactional
    @Override
    public boolean deleteProperty(long id) throws OwnerNotFoundException {
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
            throw new OwnerNotFoundException(e.getMessage());
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


}
