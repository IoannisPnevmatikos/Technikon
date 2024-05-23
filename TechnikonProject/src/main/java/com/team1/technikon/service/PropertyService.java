package com.team1.technikon.service;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.model.Property;

import java.time.LocalDate;
import java.util.List;

public interface PropertyService {

    //CREATE
    PropertyDto createProperty(long id, PropertyDto propertyDto) throws InvalidInputException, EntityFailToCreateException, EntityNotFoundException;

    //SEARCH
    PropertyDto getPropertyById(Long ownerId, String propertyId) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException;

    List<Property> getPropertyByOwnerTinNumber(Long ownerId, String tinNumber) throws EntityNotFoundException, UnauthorizedAccessException;

    List<Property> getPropertyByLocation(Long ownerId); // mallon (x,y)

    List<Property> getAllData();

    List<Property> getPropertyByRangeOfDates(LocalDate startDate, LocalDate endDate) throws EntityNotFoundException;

    //UPDATE
    PropertyDto updateProperty(Long ownerId, long id, PropertyDto propertyDto) throws EntityNotFoundException, InvalidInputException, UnauthorizedAccessException, EntityFailToCreateException;

    //DELETE
    boolean deleteProperty(Long ownerId, long id) throws EntityNotFoundException, UnauthorizedAccessException;

}
// update cascade the propertyIds to repairs table
//    ResponseApi<Property> updatePropertyId(long currentPropertyId, long newPropertyId);
//    ResponseApi<Property> updateAddress(long propertyId, String address);
//    ResponseApi<Property> updateYearOfConstruction(long propertyId, String yearOfConstruction);
//    ResponseApi<Property> updatePropertyType(long propertyId, TypeOfProperty typeOfProperty);
//    ResponseApi<Property> updatePhoto(long propertyId, String photo);
//    ResponseApi<Property> updateMapLocation(long propertyId, MapLocation mapLocation);