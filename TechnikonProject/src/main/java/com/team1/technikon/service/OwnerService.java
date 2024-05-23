package com.team1.technikon.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OwnerService extends UserDetailsService {

    //CREATE

    String addUser(SignUpDto signUpDto) throws EntityFailToCreateException;

    OwnerDto createOwner(OwnerDto ownerdto) throws EntityFailToCreateException;

    //SEARCH
    OwnerDto getOwnerById(long id) throws EntityNotFoundException;

    //UPDATE
    OwnerDto updateOwner(Long authId, Long ownerId, OwnerDto ownerDto) throws UnauthorizedAccessException, EntityFailToCreateException, EntityNotFoundException ;

    boolean updateOwnerPassword(String username, String newPw);
    //DELETE
    boolean deleteOwnerByTin(String tinNumber) throws EntityNotFoundException;

    boolean deleteOwnerById(Long id) throws EntityNotFoundException;


}
