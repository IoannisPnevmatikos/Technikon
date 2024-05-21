package com.team1.technikon.controller;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.model.Property;
import com.team1.technikon.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/property")
public class PropertyController {

    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto) throws InvalidInputException, EntityFailToCreateException {
        return ResponseEntity.ok(propertyService.createProperty(propertyDto));
    }

    @GetMapping("/propertyId")
    public ResponseEntity<PropertyDto> getProperty(@RequestBody Long ownerId, @RequestBody String propertyId) throws InvalidInputException, EntityNotFoundException, UnauthorizedAccessException {
        return ResponseEntity.ok(propertyService.getPropertyById(ownerId, propertyId));
    }

    @GetMapping("/tinNumber")
    public ResponseEntity<List<Property>> getPropertyByOwnerTinNumber(@RequestBody Long ownerId, @RequestBody String tinNumber) throws EntityNotFoundException, UnauthorizedAccessException {
        return ResponseEntity.ok(propertyService.getPropertyByOwnerTinNumber(ownerId, tinNumber));
    }

    @GetMapping("/area")
    public ResponseEntity<List<Property>> getPropertyByLocation(@RequestBody Long ownerId, @RequestBody String area) throws EntityNotFoundException {
        return ResponseEntity.ok(propertyService.getPropertyByLocation(ownerId));
    }

    @PutMapping
    public ResponseEntity<PropertyDto> updatePropertyId(@RequestBody Long ownerId, @RequestBody long id, @RequestBody PropertyDto propertyDto) throws InvalidInputException, EntityNotFoundException, UnauthorizedAccessException {
        return ResponseEntity.ok(propertyService.updateProperty(ownerId, id, propertyDto));
    }


    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestBody Long ownerId, @RequestBody long id) throws EntityNotFoundException {
        return ResponseEntity.ok(propertyService.deleteProperty(ownerId, id));
    }

    //  deleteProperty

    @GetMapping
    public ResponseEntity<List<Property>> getAllData() {
        return ResponseEntity.ok(propertyService.getAllData());
    }

}

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

