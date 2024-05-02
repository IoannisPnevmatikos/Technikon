package com.team1.technikon.service;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.enums.TypeOfProperty;

import java.util.List;

public interface PropertyService {

    Property createProperty(PropertyDto propertyDto);
    Property getPropertyById(long propertyId);
    List<Property> getPropertyByOwnerTinNumber(long tinNumber);
    List<Property> getPropertyByLocation(); // mallon (x,y)

    // update cascade the propertyIds to repairs table
    boolean updatePropertyId(long currentPropertyId, long newPropertyId);
    boolean updateAddress(long propertyId, String address);
    boolean updateYearOfConstruction(long propertyId, String yearOfConstruction);
    boolean updatePropertyType(long propertyId, TypeOfProperty typeOfProperty);
    boolean updatePhoto(long propertyId, String photo);
    boolean updateMapLocation(long propertyId, MapLocation mapLocation);
    boolean deleteProperty(long propertyId);
    List<Property> getAllData();


}
