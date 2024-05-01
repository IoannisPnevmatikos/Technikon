package com.team1.technikon.service.impl;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.enums.TypeOfProperty;
import com.team1.technikon.repository.PropertyRepository;
import com.team1.technikon.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    public Property createProperty(PropertyDto propertyDto) {
        if (propertyRepository.findById(propertyDto.propertyId()).orElseThrow(null) == null) return null;
        Property property = new Property();
        property.setPropertyId(propertyDto.propertyId());
        property.setAddress(propertyDto.address());
        property.setYearOfConstruction(propertyDto.yearOfConstruction());
        property.setTypeOfProperty(propertyDto.typeOfProperty());
        property.setPhoto(propertyDto.photo());
        property.setMapLocation(propertyDto.mapLocation());
        property.setActive(true);
        property.setOwner(propertyDto.owner()); // NA TO DOYME KAI META
        propertyRepository.save(property);
        return property;
    }

    @Override
    public Property getPropertyById(long propertyId) {
        return propertyRepository.findById(propertyId).orElseThrow(null);
    }

    @Override
    public List<Property> getPropertyByOwnerTinNumber(long tinNumber) {
        return propertyRepository.getPropertyByOwnerTinNumber(tinNumber);
    }

    @Override
    public List<Property> getPropertyByLocation() {
        return propertyRepository.getPropertyByLocation();
    }

    @Override
    public boolean updatePropertyId(long currentPropertyId, long newPropertyId) {
        return propertyRepository.updatePropertyId(currentPropertyId, newPropertyId) == 1;
    }

    @Override
    public boolean updateAddress(long propertyId, String address) {
        return propertyRepository.updateAddress(propertyId, address) == 1;
    }

    @Override
    public boolean updateYearOfConstruction(long propertyId, String yearOfConstruction) {
        return propertyRepository.updateYearOfConstruction(propertyId, yearOfConstruction) == 1;
    }

    @Override
    public boolean updatePropertyType(long propertyId, TypeOfProperty typeOfProperty) {
        return propertyRepository.updatePropertyType(propertyId, typeOfProperty) == 1;
    }

    @Override
    public boolean updatePhoto(long propertyId, String photo) {
        return propertyRepository.updatePhoto(propertyId, photo) == 1;
    }

    @Override
    public boolean updateMapLocation(long propertyId, MapLocation mapLocation) {
        return propertyRepository.updateMapLocation(propertyId, mapLocation) == 1;
    }

    @Override
    public boolean deleteProperty(long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(null);
        if (property == null) return false;
        if (property.getRepairs().isEmpty()) {
            propertyRepository.deleteById(propertyId);
            return true;
        } else {
            property.setActive(false);
            propertyRepository.save(property);
            return true;
        }
    }
}
