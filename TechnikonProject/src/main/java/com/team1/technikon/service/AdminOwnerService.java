package com.team1.technikon.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;

import java.time.LocalDate;
import java.util.List;

public interface AdminOwnerService extends OwnerService {
    //create
    String addUser(SignUpDto signUpDto) throws EntityFailToCreateException;

    String addAdmin(SignUpDto signUpDto) throws EntityFailToCreateException;

    //find-read
    List<OwnerDto> getAllOwners() throws EntityNotFoundException;

    List<OwnerDto> getAllByRole(String role) throws EntityNotFoundException;

    List<OwnerDto> getOwnersBetweenRegDate(LocalDate startDate, LocalDate endDate) throws EntityNotFoundException, InvalidInputException;

    List<OwnerDto> getOwnersActive(boolean isActive) throws EntityNotFoundException;

    OwnerDto getOwnerById(Long id) throws EntityNotFoundException;

    OwnerDto getOwnerByTinNumber(String tinNumber) throws EntityNotFoundException;

    OwnerDto getOwnerByUsername(String username) throws EntityNotFoundException;

    OwnerDto getOwnerByEmail(String email) throws EntityNotFoundException;

    OwnerDto getOwnerByFirstName(String firstName) throws EntityNotFoundException;

    OwnerDto getOwnerByLastName(String lastName) throws EntityNotFoundException;

    OwnerDto updateOwner(String username, OwnerDto ownerDto) throws EntityFailToCreateException, EntityNotFoundException;

    void deactivateOwnerByUsername(String username) throws EntityNotFoundException;

    //Delete
    boolean deleteOwnerByUsername(String username) throws EntityNotFoundException;


}
