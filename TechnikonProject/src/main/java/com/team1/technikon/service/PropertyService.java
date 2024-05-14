package com.team1.technikon.service;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.TypeOfProperty;

import java.util.List;

public interface PropertyService {

    //CREATE
    PropertyDto createProperty(PropertyDto propertyDto) throws OwnerFailToCreateException;

    //SEARCH
    PropertyDto getPropertyById(String propertyId) throws OwnerNotFoundException;
    List<Property> getPropertyByOwnerTinNumber(String tinNumber) throws OwnerNotFoundException;
    List<Property> getPropertyByLocation() throws OwnerNotFoundException; // mallon (x,y)
    List<Property> getAllData();

    //UPDATE
    // update cascade the propertyIds to repairs table
//    ResponseApi<Property> updatePropertyId(long currentPropertyId, long newPropertyId);
//    ResponseApi<Property> updateAddress(long propertyId, String address);
//    ResponseApi<Property> updateYearOfConstruction(long propertyId, String yearOfConstruction);
//    ResponseApi<Property> updatePropertyType(long propertyId, TypeOfProperty typeOfProperty);
//    ResponseApi<Property> updatePhoto(long propertyId, String photo);
//    ResponseApi<Property> updateMapLocation(long propertyId, MapLocation mapLocation);
    PropertyDto updateProperty(long id, PropertyDto propertyDto);

    //DELETE
    boolean deleteProperty(long id) throws OwnerNotFoundException;

}
