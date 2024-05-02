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

    private PropertyRepository propertyRepository;

    @Override
    public Property createProperty(PropertyDto propertyDto) {
        return null;
    }

    @Override
    public Property getPropertyById(long propertyId) {
        return null;
    }

    @Override
    public List<Property> getPropertyByOwnerTinNumber(long tinNumber) {
        return List.of();
    }

    @Override
    public List<Property> getPropertyByLocation() {
        return List.of();
    }

    @Override
    public boolean updatePropertyId(long currentPropertyId, long newPropertyId) {
        return false;
    }

    @Override
    public boolean updateAddress(long propertyId, String address) {
        return false;
    }

    @Override
    public boolean updateYearOfConstruction(long propertyId, String yearOfConstruction) {
        return false;
    }

    @Override
    public boolean updatePropertyType(long propertyId, TypeOfProperty typeOfProperty) {
        return false;
    }

    @Override
    public boolean updatePhoto(long propertyId, String photo) {
        return false;
    }

    @Override
    public boolean updateMapLocation(long propertyId, MapLocation mapLocation) {
        return false;
    }

    @Override
    public boolean deleteProperty(long propertyId) {
        return false;
    }
}
