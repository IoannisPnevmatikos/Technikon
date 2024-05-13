package com.team1.technikon.service;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.TypeOfProperty;

import java.util.List;

public interface PropertyService {

    //CREATE
    ResponseApi<Property> createProperty(PropertyDto propertyDto);

    //SEARCH
    ResponseApi<Property> getPropertyById(long propertyId);
    List<Property> getPropertyByOwnerTinNumber(long tinNumber);
    List<Property> getPropertyByLocation(); // mallon (x,y)
    List<Property> getAllData();

    //UPDATE
    // update cascade the propertyIds to repairs table
//    ResponseApi<Property> updatePropertyId(long currentPropertyId, long newPropertyId);
//    ResponseApi<Property> updateAddress(long propertyId, String address);
//    ResponseApi<Property> updateYearOfConstruction(long propertyId, String yearOfConstruction);
//    ResponseApi<Property> updatePropertyType(long propertyId, TypeOfProperty typeOfProperty);
//    ResponseApi<Property> updatePhoto(long propertyId, String photo);
//    ResponseApi<Property> updateMapLocation(long propertyId, MapLocation mapLocation);
    ResponseApi<Property> updateProperty(long id, PropertyDto propertyDto);

    //DELETE
    ResponseApi<Property> deleteProperty(long propertyId);

}
