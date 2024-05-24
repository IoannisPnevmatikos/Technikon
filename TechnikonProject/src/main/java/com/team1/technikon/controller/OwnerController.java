package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
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


    @PutMapping
    public ResponseEntity<OwnerDto> createOwner(@RequestBody OwnerDto ownerDto, Authentication authentication) throws EntityFailToCreateException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        return ResponseEntity.ok(ownerService.createOwner(ownerDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(Authentication authentication) throws EntityNotFoundException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        return ResponseEntity.ok(ownerService.getOwnerById(userInfoDetails.getId()));
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable("ownerId") long ownerId,@RequestBody OwnerDto ownerDto,Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException, EntityFailToCreateException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        Long authId = userInfoDetails.getId();
        return ResponseEntity.ok(ownerService.updateOwner(authId,ownerId,ownerDto));
    }

    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteOwner(Authentication authentication) throws EntityNotFoundException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        return ResponseEntity.ok(ownerService.deleteOwnerById(userInfoDetails.getId()));
    }

}
