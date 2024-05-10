package com.team1.technikon.service.impl;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.mapper.TechnikonMapper;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.enums.TypeOfProperty;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final TechnikonMapper technikonMapper;

    @Override
    public ResponseApi<Property> createProperty(PropertyDto propertyDto) {
        if (propertyRepository.findById(propertyDto.propertyId()).orElse(null) != null) return new ResponseApi<>(0, "Property already exists.", null);
        Property property = technikonMapper.toProperty(propertyDto);
        propertyRepository.save(property);
        return new ResponseApi<>(0, "New property created!", property);
    }

    @Override
    public ResponseApi<Property> getPropertyById(long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
        return new ResponseApi<>(0, "Requested property found!", property);
    }

    @Override
    public List<Property> getPropertyByOwnerTinNumber(long tinNumber) {
        List<Property> properties = propertyRepository.getPropertyByOwnerTinNumber(tinNumber);
        List<Property> response = new ArrayList<>();
        properties.forEach(property -> {
            if (property.isActive()) response.add(property);
        });
        return response;
    }

    @Override
    public List<Property> getPropertyByLocation() {
        List<Property> properties = propertyRepository.getPropertyByLocation();
        List<Property> response = new ArrayList<>();
        properties.forEach(property -> {
            if (property.isActive()) response.add(property);
        });
        return response;
    }

    @Override
    public ResponseApi<Property> updatePropertyId(long currentPropertyId, long newPropertyId) {
        Property property = propertyRepository.findById(currentPropertyId).orElseThrow(null);
        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
        propertyRepository.updatePropertyId(currentPropertyId, newPropertyId);
        property.setPropertyId(newPropertyId);
        return new ResponseApi<>(0, "Property updated successfully!", property);
    }

    @Override
    public ResponseApi<Property> updateAddress(long propertyId, String address) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
        propertyRepository.updateAddress(propertyId, address);
        property.setAddress(address);
        return new ResponseApi<>(0, "Property updated successfully!", property);
    }

    @Override
    public ResponseApi<Property> updateYearOfConstruction(long propertyId, String yearOfConstruction) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
        propertyRepository.updateYearOfConstruction(propertyId, yearOfConstruction);
        property.setYearOfConstruction(yearOfConstruction);
        return new ResponseApi<>(0, "Property updated successfully!", property);
    }

    @Override
    public ResponseApi<Property> updatePropertyType(long propertyId, TypeOfProperty typeOfProperty) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
        propertyRepository.updatePropertyType(propertyId, typeOfProperty);
        property.setTypeOfProperty(typeOfProperty);
        return new ResponseApi<>(0, "Property updated successfully!", property);
    }

    @Override
    public ResponseApi<Property> updatePhoto(long propertyId, String photo) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
        propertyRepository.updatePhoto(propertyId, photo);
        property.setPhoto(photo);
        return new ResponseApi<>(0, "Property updated successfully!", property);
    }

    @Override
    public ResponseApi<Property> updateMapLocation(long propertyId, MapLocation mapLocation) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);
        if (!property.isActive()) return new ResponseApi<>(0, "Requested property is no longer active.", null);
        propertyRepository.updateMapLocation(propertyId, mapLocation);
        property.setMapLocation(mapLocation);
        return new ResponseApi<>(0, "Property updated successfully!", property);
    }

    @Override
    public ResponseApi<Property> deleteProperty(long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
        if (property == null) return new ResponseApi<>(0, "Requested property does not exist.", null);;
        if (property.getRepairs().isEmpty()) {
            propertyRepository.deleteById(propertyId);
            return new ResponseApi<>(0, "Property deleted successfully!", null);
        } else {
            property.setActive(false);
            propertyRepository.save(property);
            return new ResponseApi<>(0, "Property deactivated!", null);
        }
    }

    @Override
    public List<Property> getAllData() {
        return propertyRepository.findAll();
    }
}
