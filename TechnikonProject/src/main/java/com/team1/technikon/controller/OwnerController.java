package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.securityservice.service.UserInfoDetails;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/owner")
@CrossOrigin
public class OwnerController {

    private final OwnerService ownerService;


    @PutMapping("")
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto, Authentication authentication) throws EntityFailToCreateException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        if (userInfoDetails.getUsername().equals(ownerDto.username()))
            return ResponseEntity.ok(ownerService.createOwner(ownerDto));
        else throw new EntityFailToCreateException("Entity failed to create");
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable("ownerId") long ownerId, Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        Long authId = userInfoDetails.getId();
        return ResponseEntity.ok(ownerService.getOwnerById(authId, ownerId));
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable("ownerId") long ownerId, @RequestBody OwnerDto ownerDto, Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException, InvalidInputException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        Long authId = userInfoDetails.getId();
        return ResponseEntity.ok(ownerService.updateOwner(authId, ownerId, ownerDto));
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Boolean> deleteOwner(@PathVariable("ownerId") long ownerId, Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        Long authId = userInfoDetails.getId();
        return ResponseEntity.ok(ownerService.deleteOwnerById(authId, ownerId));
    }

//    @DeleteMapping("")
//    public ResponseEntity<Boolean> deleteOwner(Authentication authentication) throws EntityNotFoundException {
//        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
//        return ResponseEntity.ok(ownerService.deleteOwnerById(userInfoDetails.getId()));
//    }

}
