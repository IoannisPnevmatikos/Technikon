package com.team1.technikon.service;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.dto.ResponseApi;
import com.team1.technikon.exception.*;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.Repair;
import com.team1.technikon.model.enums.TypeOfProperty;

import java.util.List;

public interface PropertyService {

    //CREATE
    PropertyDto createProperty(PropertyDto propertyDto) throws InvalidInputException, EntityFailToCreateException;

    //SEARCH
    PropertyDto getPropertyById(String propertyId) throws EntityNotFoundException, InvalidInputException;
    List<Property> getPropertyByOwnerTinNumber(String tinNumber) throws EntityNotFoundException;
    List<Property> getPropertyByLocation() throws EntityNotFoundException; // mallon (x,y)
    List<Property> getAllData();

    //UPDATE
    // update cascade the propertyIds to repairs table
//    ResponseApi<Property> updatePropertyId(long currentPropertyId, long newPropertyId);
//    ResponseApi<Property> updateAddress(long propertyId, String address);
//    ResponseApi<Property> updateYearOfConstruction(long propertyId, String yearOfConstruction);
//    ResponseApi<Property> updatePropertyType(long propertyId, TypeOfProperty typeOfProperty);
//    ResponseApi<Property> updatePhoto(long propertyId, String photo);
//    ResponseApi<Property> updateMapLocation(long propertyId, MapLocation mapLocation);
    PropertyDto updateProperty(long id, PropertyDto propertyDto) throws EntityNotFoundException, InvalidInputException;

    //DELETE
    boolean deleteProperty(long id) throws EntityNotFoundException;

}
