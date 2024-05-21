package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto) throws OwnerFailToCreateException {
        return ResponseEntity.ok(ownerService.createOwner(ownerDto));
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> findAll() throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{email}")
    public ResponseEntity<OwnerDto> findByEmail(@PathVariable String email) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByEmail(email));
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<OwnerDto> findByFirstName(@PathVariable String firstName) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByFirstName(firstName));
    }


    @GetMapping("/{lastName}")
    public ResponseEntity<OwnerDto> findByLastName(@PathVariable String lastName) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByLastName(lastName));
    }

    //
    @GetMapping("/active")
    public ResponseEntity<List<OwnerDto>> getActiveOwners() throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.getAllActiveOwners());
    }

    @GetMapping("/{tinNumber}")
    public ResponseEntity<OwnerDto> findByTinNumber(@PathVariable String tinNumber) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByTin(tinNumber));
    }

    @GetMapping("/{username}")
    public ResponseEntity<OwnerDto> findByUsername(@PathVariable String username) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByUsername(username));
    }

    //
    @PutMapping("/{tinNumber}/address")
    public ResponseEntity<Boolean> updateAddress(@PathVariable String tinNumber, @RequestBody String address) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.updateAddress(tinNumber, address));
    }

    @PutMapping("/{tinNumber}/phone")
    public ResponseEntity<Boolean> updatePhone(@PathVariable String tinNumber, @RequestBody String phone) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.updateOwnerByPhone(tinNumber, phone));
    }

    @DeleteMapping("/{tinNumber}")
    public ResponseEntity<Boolean> deleteOwner(@PathVariable String tinNumber) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.deleteOwner(tinNumber));
    }


}
