package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/owner")
@CrossOrigin
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto) throws EntityFailToCreateException {
        return ResponseEntity.ok(ownerService.createOwner(ownerDto));
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> findAll() throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{email}")
    public ResponseEntity<OwnerDto> findByEmail(@PathVariable String email) throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByEmail(email));
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<OwnerDto> findByFirstName(@PathVariable String firstName) throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByFirstName(firstName));
    }


    @GetMapping("/{lastName}")
    public ResponseEntity<OwnerDto> findByLastName(@PathVariable String lastName) throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByLastName(lastName));
    }

    //
    @GetMapping("/active")
    public ResponseEntity<List<OwnerDto>> getActiveOwners() throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.getAllActiveOwners());
    }

    @GetMapping("/{tinNumber}")
    public ResponseEntity<OwnerDto> findByTinNumber(@PathVariable String tinNumber) throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByTin(tinNumber));
    }

    @GetMapping("/{username}")
    public ResponseEntity<OwnerDto> findByUsername(@PathVariable String username) throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.getOwnerByUsername(username));
    }

    @PutMapping("/{tinNumber}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable String tinNumber, @RequestBody OwnerDto ownerDto) throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.updateOwner(tinNumber, ownerDto));
    }

    @DeleteMapping("/{tinNumber}")
    public ResponseEntity<Boolean> deleteOwner(@PathVariable String tinNumber) throws EntityNotFoundException {
        return ResponseEntity.ok(ownerService.deleteOwner(tinNumber));
    }


}
