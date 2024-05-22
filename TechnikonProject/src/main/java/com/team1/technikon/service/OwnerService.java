package com.team1.technikon.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface OwnerService extends UserDetailsService {

    //CREATE

    String addUser(SignUpDto signUpDto) throws OwnerFailToCreateException;

    String addAdmin(SignUpDto signUpDto);

    OwnerDto getOwnerByUsername(String username);

    OwnerDto createOwner(OwnerDto ownerdto) throws OwnerFailToCreateException;

    //SEARCH
    OwnerDto getOwnerByTin(String tinNumber) throws OwnerNotFoundException;

    OwnerDto getOwnerByEmail(String email) throws OwnerNotFoundException;

    OwnerDto getOwnerByFirstName(String firstName) throws OwnerNotFoundException;

    OwnerDto getOwnerByLastName(String lastName) throws OwnerNotFoundException;

    // GET ALL DATA
    List<OwnerDto> getAllOwners() throws OwnerNotFoundException;

    List<OwnerDto> getAllActiveOwners() throws OwnerNotFoundException;

    //UPDATE
    OwnerDto updateOwner(String tinNumber, OwnerDto ownerDto) throws OwnerNotFoundException;

    boolean updateOwnerPassword(String username, String newPw);
    //DELETE
    boolean deleteOwner(String tinNumber) throws OwnerNotFoundException;


}
