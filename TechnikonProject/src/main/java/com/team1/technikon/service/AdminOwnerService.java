package com.team1.technikon.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface AdminOwnerService extends OwnerService {
    //create
    String addUser(SignUpDto signUpDto) throws EntityFailToCreateException;

    String addAdmin(SignUpDto signUpDto) throws EntityFailToCreateException;

    //find-read
    List<OwnerDto> getAllOwners() throws EntityNotFoundException;

    List<OwnerDto> getAllByRole(String role) throws EntityNotFoundException;

    List<OwnerDto> getOwnersBetweenRegDate(LocalDate startDate, LocalDate endDate) throws EntityNotFoundException;

    List<OwnerDto> getOwnersActive(boolean isActive) throws EntityNotFoundException;

    OwnerDto getOwnerById(Long id) throws EntityNotFoundException;

    OwnerDto getOwnerByTinNumber(String tinNumber) throws EntityNotFoundException;

    OwnerDto getOwnerByUsername(String username) throws EntityNotFoundException;

    OwnerDto getOwnerByEmail(String email) throws EntityNotFoundException;

    OwnerDto getOwnerByFirstName(String firstName) throws EntityNotFoundException;

    OwnerDto getOwnerByLastName(String lastName) throws EntityNotFoundException;

    OwnerDto updateOwner(Long ownerId, OwnerDto ownerDto) throws EntityFailToCreateException, EntityNotFoundException;

    void deactivateOwnerById(Long id) throws EntityNotFoundException;

    //Delete
    boolean deleteOwnerById(Long id) throws EntityNotFoundException;


}
