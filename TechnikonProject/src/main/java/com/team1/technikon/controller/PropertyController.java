package com.team1.technikon.controller;

import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.model.MapLocation;
import com.team1.technikon.model.Property;
import com.team1.technikon.model.enums.TypeOfProperty;
import com.team1.technikon.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/property")
public class PropertyController {

    private PropertyService propertyService;

    @PostMapping
    public Property addProperty(@RequestBody PropertyDto propertyDto) {
       return propertyService.createProperty(propertyDto);

    }

    @GetMapping("/propertyId/{propertyId}")
    public Property getProperty(@PathVariable long propertyId) {
        return propertyService.getPropertyById(propertyId);
    }

    @GetMapping("/tinNumber/{tinNumber}") //den doyleyei!!!!!!!!
    public List<Property> getPropertyByOwnerTinNumber(@PathVariable long tinNumber) {
        return propertyService.getPropertyByOwnerTinNumber(tinNumber);
    }

    @GetMapping("/area/{area}")
    public List<Property> getPropertyByLocation(@PathVariable String area) {
        return propertyService.getPropertyByLocation();
    }

    @PutMapping("/propertyId/{propertyId}/{newPropertyId}")
    public boolean updatePropertyId(@PathVariable long propertyId, @PathVariable long newPropertyId) {
        return propertyService.updatePropertyId(propertyId, newPropertyId);
    }

    @PutMapping("/address/{propertyId}/{address}")
    public boolean updateAddress(@PathVariable long propertyId, @PathVariable  String address) {
        return propertyService.updateAddress(propertyId, address);
    }
    @PutMapping("/yearOfConstruction/{propertyId}/{yearOfConstruction}")
    public boolean updateYearOfConstruction(@PathVariable long propertyId, @PathVariable  String yearOfConstruction) {
        return propertyService.updateYearOfConstruction(propertyId, yearOfConstruction );
    }
    @PutMapping("/typeOfProperty/{propertyId}/{typeOfProperty}")
    public boolean updatePropertyType(@PathVariable long propertyId, @PathVariable TypeOfProperty typeOfProperty) {
        return propertyService.updatePropertyType(propertyId, typeOfProperty);
    }
    @PutMapping("/photo/{propertyId}/{photo}")
    public boolean updatePhoto(@PathVariable long propertyId, @PathVariable  String photo) {
        return propertyService.updatePhoto(propertyId, photo);
    }
    @PutMapping("/mapLocation/{propertyId}")
    public boolean updateMapLocation(@PathVariable long propertyId, @RequestBody MapLocation mapLocation) {
        return propertyService.updateMapLocation(propertyId, mapLocation);
    }

    @DeleteMapping("/{propertyId}")
    public boolean delete(@PathVariable long propertyId) {
        return propertyService.deleteProperty(propertyId);
    }

          //  deleteProperty

    @GetMapping
    public List<Property> getAllData() {
        return propertyService.getAllData();
    }

}
