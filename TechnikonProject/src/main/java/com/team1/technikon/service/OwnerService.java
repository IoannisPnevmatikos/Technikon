package com.team1.technikon.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface OwnerService extends UserDetailsService {

    //CREATE

    String addUser(SignUpDto signUpDto) throws EntityFailToCreateException;

    String addAdmin(SignUpDto signUpDto) throws EntityFailToCreateException;

    OwnerDto getOwnerByUsername(String username) throws EntityNotFoundException;

    OwnerDto createOwner(OwnerDto ownerdto) throws EntityFailToCreateException;

    //SEARCH
    OwnerDto getOwnerByTin(String tinNumber) throws EntityNotFoundException;

    OwnerDto getOwnerByEmail(String email) throws EntityNotFoundException;

    OwnerDto getOwnerByFirstName(String firstName) throws EntityNotFoundException;

    OwnerDto getOwnerByLastName(String lastName) throws EntityNotFoundException;

    // GET ALL DATA
    List<OwnerDto> getAllOwners() throws EntityNotFoundException;

    List<OwnerDto> getAllActiveOwners() throws EntityNotFoundException;

    //UPDATE
    OwnerDto updateOwner(String tinNumber, OwnerDto ownerDto) throws EntityNotFoundException;

    boolean updateOwnerPassword(String username, String newPw);
    //DELETE
    boolean deleteOwner(String tinNumber) throws EntityNotFoundException;


}
