package com.team1.technikon.controller;


import com.team1.technikon.dto.PropertyDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.model.Property;
import com.team1.technikon.service.OwnerService;
import com.team1.technikon.service.PropertyService;
import com.team1.technikon.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/admin")
public class AdminPropertyController {

    private final OwnerService ownerService;
    private PropertyService propertyService;
    private final RepairService repairService;

    @PostMapping("/property/{id}")
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto, @PathVariable long id) throws InvalidInputException, EntityFailToCreateException, EntityNotFoundException {
        return ResponseEntity.ok(propertyService.createProperty(id, propertyDto));
    }

    @GetMapping("/property/propertyId/{propertyId}")
    public ResponseEntity<PropertyDto> getProperty(@PathVariable String propertyId) throws InvalidInputException, EntityNotFoundException, UnauthorizedAccessException {
        return ResponseEntity.ok(propertyService.getPropertyById(null, propertyId));
    }

    @GetMapping("/property/tinNumber/{tinNumber}")
    public ResponseEntity<List<Property>> getPropertyByOwnerTinNumber(@RequestBody String tinNumber) throws EntityNotFoundException {
        return ResponseEntity.ok(propertyService.getPropertyByOwnerTinNumber(null, tinNumber));
    }

    @GetMapping("/property/rangeOfDates/{startDate}/{endDate}")
    public ResponseEntity<List<Property>> getPropertyByRangeOfDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws EntityNotFoundException {
        return ResponseEntity.ok(propertyService.getPropertyByRangeOfDates(startDate, endDate));
    }

    @PutMapping("/property/{id}")
    public ResponseEntity<PropertyDto> updatePropertyId(@PathVariable long id, @RequestBody PropertyDto propertyDto) throws InvalidInputException, EntityNotFoundException, UnauthorizedAccessException, EntityFailToCreateException {
        return ResponseEntity.ok(propertyService.updateProperty(null, id, propertyDto));
    }

    @DeleteMapping("/property/{id}")
    public ResponseEntity<Boolean> deleteProperty(@PathVariable long id) throws EntityNotFoundException, UnauthorizedAccessException {
        return ResponseEntity.ok(propertyService.deleteProperty(null, id));
    }

    @GetMapping("/property")
    public ResponseEntity<List<Property>> getAllData() {
        return ResponseEntity.ok(propertyService.getAllData());
    }

}
