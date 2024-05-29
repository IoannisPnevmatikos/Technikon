package com.team1.technikon.controller;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.securityservice.service.UserInfoDetails;
import com.team1.technikon.service.OwnerService;
import com.team1.technikon.service.impl.OwnerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/owner")
@CrossOrigin
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;
    private static Logger logger = LoggerFactory.getLogger(OwnerController.class);

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

    @GetMapping("/tin/{tinNumber}")
    public ResponseEntity<OwnerDto> getOwnerByTinNumber(@PathVariable("tinNumber") String tin, Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        Long authId = userInfoDetails.getId();
        return ResponseEntity.ok(ownerService.getOwnerByTin(authId, tin));
    }


    @PutMapping("/{username}")
    public ResponseEntity<OwnerDto> updateOwner(@PathVariable("username") String username, @RequestBody OwnerDto ownerDto, Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException, InvalidInputException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        Long authId = userInfoDetails.getId();
        return ResponseEntity.ok(ownerService.updateOwner(authId, username, ownerDto));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Boolean> deleteOwner(@PathVariable("username") String username, Authentication authentication) throws EntityNotFoundException, UnauthorizedAccessException {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        long authId = userInfoDetails.getId();
        logger.info("ID AUTH {}", authId);
        return ResponseEntity.ok(ownerService.deleteOwnerByUsername(authId, username));
    }

//    @DeleteMapping("")
//    public ResponseEntity<Boolean> deleteOwner(Authentication authentication) throws EntityNotFoundException {
//        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
//        return ResponseEntity.ok(ownerService.deleteOwnerById(userInfoDetails.getId()));
//    }

}
