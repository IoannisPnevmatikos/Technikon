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
    PropertyDto getPropertyById(Long ownerId, String propertyId) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException ;
    List<Property> getPropertyByOwnerTinNumber(Long ownerId, String tinNumber) throws EntityNotFoundException, UnauthorizedAccessException;
    List<Property> getPropertyByLocation(Long ownerId) throws EntityNotFoundException; // mallon (x,y)
    List<Property> getAllData();

    //UPDATE
    PropertyDto updateProperty(Long ownerId, long id, PropertyDto propertyDto) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException;

    //DELETE
    boolean deleteProperty(Long ownerId, long id) throws EntityNotFoundException;

}
// update cascade the propertyIds to repairs table
//    ResponseApi<Property> updatePropertyId(long currentPropertyId, long newPropertyId);
//    ResponseApi<Property> updateAddress(long propertyId, String address);
//    ResponseApi<Property> updateYearOfConstruction(long propertyId, String yearOfConstruction);
//    ResponseApi<Property> updatePropertyType(long propertyId, TypeOfProperty typeOfProperty);
//    ResponseApi<Property> updatePhoto(long propertyId, String photo);
//    ResponseApi<Property> updateMapLocation(long propertyId, MapLocation mapLocation);