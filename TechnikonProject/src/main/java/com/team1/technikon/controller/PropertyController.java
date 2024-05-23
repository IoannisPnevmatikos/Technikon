package com.team1.technikon.controller;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.model.Property;
import com.team1.technikon.securityservice.service.UserInfoDetails;
import com.team1.technikon.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto, Authentication authentication) throws InvalidInputException, EntityFailToCreateException, EntityNotFoundException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long id = userInfoDetails.getId();
        return ResponseEntity.ok(propertyService.createProperty(id, propertyDto));
    }

    @GetMapping("/propertyId/{propertyId}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable String propertyId, Authentication authentication) throws InvalidInputException, EntityNotFoundException, UnauthorizedAccessException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long id = userInfoDetails.getId();
        return ResponseEntity.ok(propertyService.getPropertyById(id, propertyId));
    }

    @GetMapping("/tinNumber/{tinNumber}")
    public ResponseEntity<List<Property>> getPropertyByOwnerTinNumber(@PathVariable String tinNumber, Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long id = userInfoDetails.getId();
        return ResponseEntity.ok(propertyService.getPropertyByOwnerTinNumber(id, tinNumber));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDto> updatePropertyId(@PathVariable long id, @RequestBody PropertyDto propertyDto, Authentication authentication) throws InvalidInputException, EntityNotFoundException, UnauthorizedAccessException, EntityFailToCreateException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long ownerId = userInfoDetails.getId();
        return ResponseEntity.ok(propertyService.updateProperty(ownerId, id, propertyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProperty(@PathVariable long id, Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long ownerId = userInfoDetails.getId();
        return ResponseEntity.ok(propertyService.deleteProperty(ownerId, id));
    }

}

    //  deleteProperty
    ////    @GetMapping("/area")
    ////    public ResponseEntity<List<Property>> getPropertyByLocation(@RequestBody Long ownerId, @RequestBody String area, Authentication authentication) throws EntityNotFoundException {
    ////        return ResponseEntity.ok(propertyService.getPropertyByLocation(ownerId));
    ////    }


//    @PutMapping("/propertyId/{propertyId}/{newPropertyId}")
//    public ResponseApi<Property> updatePropertyId(@PathVariable long propertyId, @PathVariable long newPropertyId) {
//        return propertyService.updatePropertyId(propertyId, newPropertyId);
//    }
//
//    @PutMapping("/address/{propertyId}/{address}")
//    public ResponseApi<Property> updateAddress(@PathVariable long propertyId, @PathVariable  String address) {
//        return propertyService.updateAddress(propertyId, address);
//    }
//    @PutMapping("/yearOfConstruction/{propertyId}/{yearOfConstruction}")
//    public ResponseApi<Property> updateYearOfConstruction(@PathVariable long propertyId, @PathVariable  String yearOfConstruction) {
//        return propertyService.updateYearOfConstruction(propertyId, yearOfConstruction );
//    }
//    @PutMapping("/typeOfProperty/{propertyId}/{typeOfProperty}")
//    public ResponseApi<Property> updatePropertyType(@PathVariable long propertyId, @PathVariable TypeOfProperty typeOfProperty) {
//        return propertyService.updatePropertyType(propertyId, typeOfProperty);
//    }
//    @PutMapping("/photo/{propertyId}/{photo}")
//    public ResponseApi<Property> updatePhoto(@PathVariable long propertyId, @PathVariable  String photo) {
//        return propertyService.updatePhoto(propertyId, photo);
//    }
//    @PutMapping("/mapLocation/{propertyId}")
//    public ResponseApi<Property> updateMapLocation(@PathVariable long propertyId, @RequestBody MapLocation mapLocation) {
//        return propertyService.updateMapLocation(propertyId, mapLocation);
//    }

