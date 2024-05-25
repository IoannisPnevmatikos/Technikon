package com.team1.technikon.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.securityservice.dto.ChangePwRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OwnerService extends UserDetailsService {

    //CREATE

    String addUser(SignUpDto signUpDto) throws EntityFailToCreateException;

    OwnerDto createOwner(OwnerDto ownerdto) throws EntityFailToCreateException;

    //SEARCH
    OwnerDto getOwnerById(Long authId, long id) throws EntityNotFoundException,UnauthorizedAccessException;

    //UPDATE
    OwnerDto updateOwner(Long authId, Long ownerId, OwnerDto ownerDto) throws UnauthorizedAccessException, InvalidInputException, EntityNotFoundException ;

    String updateOwnerPassword(ChangePwRequestDto changePwRequestDto)  throws EntityNotFoundException,InvalidInputException;
    //DELETE
    boolean deleteOwnerByTin(String tinNumber) throws EntityNotFoundException;

    boolean deleteOwnerById(Long authId,Long id) throws EntityNotFoundException, UnauthorizedAccessException;


}
