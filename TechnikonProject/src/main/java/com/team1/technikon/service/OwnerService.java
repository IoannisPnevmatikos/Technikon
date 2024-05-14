package com.team1.technikon.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;

import java.util.List;

public interface OwnerService {

    //CREATE
    OwnerDto createOwner(OwnerDto ownerDto) throws OwnerFailToCreateException;
    //SEARCH
    OwnerDto getOwnerByTin(String tinNumber) throws OwnerNotFoundException;

    OwnerDto getOwnerByEmail(String email) throws OwnerNotFoundException;

    OwnerDto getOwnerByUsername(String username) throws OwnerNotFoundException;

    //UPDATE
    boolean updateAddress(String tinNumber, String address) throws OwnerNotFoundException;

    boolean updateEmail(String tinNumber, String email) throws OwnerNotFoundException;

    boolean updatePassword(String tinNumber, String password) throws OwnerNotFoundException;

    boolean updateOwner(String tinNumber,OwnerDto ownerDto) throws OwnerNotFoundException;

    //DELETE
    boolean deleteOwner(String tinNumber) throws OwnerNotFoundException;

    // GET ALL DATA
    List<OwnerDto> getAllData() throws OwnerNotFoundException;

    List<OwnerDto> getAllActiveOwners() throws OwnerNotFoundException;
}
