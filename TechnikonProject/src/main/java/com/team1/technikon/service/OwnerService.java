package com.team1.technikon.service;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.model.Owner;

import java.util.List;

public interface OwnerService {

    //CREATE
    Owner createOwner(OwnerDto ownerDto);

    //SEARCH
    Owner getOwnerByTin(long tinNumber);

    Owner getOwnerByEmail(String email);

    Owner getOwnerByUsername(String username);

    //UPDATE
    boolean updateAddress(long tinNumber, String address);

    boolean updateEmail(long tinNumber, String email);

    boolean updatePassword(long tinNumber, String password);

    //DELETE
    boolean deleteOwner(long tinNumber);

    // GET ALL DATA
    List<Owner> getAllData();
}
