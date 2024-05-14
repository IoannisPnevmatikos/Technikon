package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.model.Owner;
import org.springframework.http.ResponseEntity;

import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<OwnerDto>> allData() throws OwnerNotFoundException {
        return ResponseEntity.ok( ownerService.getAllData());
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getActiveOwners() throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.getAllActiveOwners());
    }


    @GetMapping("/tinNumber/{tinNumber}")
    public ResponseEntity<OwnerDto> findByTinNumber(@PathVariable String tinNumber) throws OwnerNotFoundException {
        return ResponseEntity.ok( ownerService.getOwnerByTin(tinNumber));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<OwnerDto> findByEmail(@PathVariable String email) throws OwnerNotFoundException {

        return ResponseEntity.ok(ownerService.getOwnerByEmail(email));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<OwnerDto> findByUsername(@PathVariable String username) throws OwnerNotFoundException {
        return ResponseEntity.ok( ownerService.getOwnerByUsername(username));
    }

    @PutMapping("/address/{tinNumber}/{address}")
    public ResponseEntity<Boolean> updateAddress(@PathVariable String tinNumber, @PathVariable String address) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.updateAddress(tinNumber, address));
    }

    @PutMapping("/email/{tinNumber}/{email}")
    public ResponseEntity<Boolean> updateEmail(@PathVariable String tinNumber, @PathVariable String email) throws OwnerNotFoundException {
        return ResponseEntity.ok( ownerService.updateEmail(tinNumber, email));
    }

    //LOGIKA EINAI LATHOS IMPL
    @PutMapping("/password/{tinNumber}/{password}")
    public ResponseEntity<Boolean> updatePassword(@PathVariable String tinNumber, @PathVariable String password) throws OwnerNotFoundException {
        return ResponseEntity.ok( ownerService.updatePassword(tinNumber, password));
    }

    @PutMapping("/{tinNumber}")
    public ResponseEntity<Boolean> updateOwner(@PathVariable String tinNumber,@RequestBody OwnerDto ownerDto) throws OwnerNotFoundException {
        return ResponseEntity.ok(ownerService.updateOwner(tinNumber,ownerDto));
    }

    @DeleteMapping("/{tinNumber}")
    public ResponseEntity<Boolean> deleteOwner(@PathVariable String tinNumber) throws OwnerNotFoundException {
        return ResponseEntity.ok( ownerService.deleteOwner(tinNumber));
    }

}
