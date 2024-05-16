package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.model.Owner;
import com.team1.technikon.securityservice.service.UserService;
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

//    private final OwnerService ownerService;
//
//    @PostMapping
//    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto) throws OwnerFailToCreateException {
//        return ResponseEntity.ok(ownerService.createOwner(ownerDto));
//
//    }
//
//    @GetMapping
//    public ResponseEntity<List<OwnerDto>> allData() throws OwnerNotFoundException {
//        return ResponseEntity.ok( ownerService.getAllData());
//    }
//
////    @GetMapping("/active")
////    public ResponseEntity<List<OwnerDto>> getActiveOwners() throws OwnerNotFoundException {
////        return ResponseEntity.ok(ownerService.getAllActiveOwners());
////    }
//
//    @GetMapping("/tinNumber/{tinNumber}")
//    public ResponseEntity<OwnerDto> findByTinNumber(@PathVariable String tinNumber) throws OwnerNotFoundException {
//        return ResponseEntity.ok( ownerService.getOwnerByTin(tinNumber));
//    }
//
//    @PutMapping("/address/{tinNumber}/{address}")
//    public ResponseEntity<Boolean> updateAddress(@PathVariable String tinNumber, @PathVariable String address) throws OwnerNotFoundException {
//        return ResponseEntity.ok(ownerService.updateAddress(tinNumber, address));
//    }
//
//    @DeleteMapping("/{tinNumber}")
//    public ResponseEntity<Boolean> deleteOwner(@PathVariable String tinNumber) throws OwnerNotFoundException {
//        return ResponseEntity.ok( ownerService.deleteOwner(tinNumber));
//    }



}
