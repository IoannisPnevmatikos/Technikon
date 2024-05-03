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

    @GetMapping("/{id}")
    public Property getProperty(@PathVariable(value = "id") Long propertyId) {
        return propertyService.getPropertyById(propertyId);
    }

    @GetMapping("/byOwner/{tinNumber}") //den doyleyei!!!!!!!!
    public List<Property> getPropertyByOwnerTinNumber(@PathVariable Long tinNumber) {
        return propertyService.getPropertyByOwnerTinNumber(tinNumber);
    }

    @GetMapping("/byLocation/{area}")
    public List<Property> getPropertyByLocation(@PathVariable String area) {
        return propertyService.getPropertyByLocation();
    }

    @PutMapping("/updatePropertyId/{propertyId}/{newPropertyId}")
    public boolean updatePropertyId(@PathVariable Long propertyId,@PathVariable Long newPropertyId) {
        return propertyService.updatePropertyId(propertyId, newPropertyId);
    }

    @PutMapping("/updateAddress/{propertyId}")
    public boolean updateAddress(@PathVariable Long propertyId, @RequestBody  String address) {
        return propertyService.updateAddress(propertyId, address);
    }
    @PutMapping("/updateYearOfConstruction/{propertyId}")
    public boolean updateYearOfConstruction(@PathVariable Long propertyId, @RequestBody  String yearOfConstruction) {
        return propertyService.updateYearOfConstruction(propertyId, yearOfConstruction );
    }
    @PutMapping("/updatePropertyType/{propertyId}")
    public boolean updatePropertyType(@PathVariable Long propertyId, @RequestBody TypeOfProperty typeOfProperty) {
        return propertyService.updatePropertyType(propertyId, typeOfProperty);
    }
    @PutMapping("/updatePhoto/{propertyId}")
    public boolean updatePhoto(@PathVariable Long propertyId, @RequestBody  String photo) {
        return propertyService.updatePhoto(propertyId, photo);
    }
    @PutMapping("/updateMapLocation/{propertyId}")
    public boolean updateMapLocation(@PathVariable Long propertyId, @RequestBody MapLocation mapLocation) {
        return propertyService.updateMapLocation(propertyId, mapLocation);
    }

    @DeleteMapping("/delete/{propertyId}")
    public boolean delete(@PathVariable Long propertyId) {
        return propertyService.deleteProperty(propertyId);
    }

          //  deleteProperty

    @GetMapping("/allData")
    public List<Property> getAllData() {
        return propertyService.getAllData();
    }


}
