package com.team1.technikon.service.impl;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.model.Owner;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Owner createOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setTinNumber(ownerDto.tinNumber());
        owner.setFirstName(ownerDto.firstName());
        owner.setLastName(ownerDto.lastName());
        owner.setAddress(ownerDto.address());
        owner.setPhone(ownerDto.phone());
        owner.setEmail(ownerDto.email());
        owner.setUsername(ownerDto.username());
        owner.setPassword(ownerDto.password());
        owner.setActive(true);
        ownerRepository.save(owner);
        return owner;
    }

    @Override
    public Owner getOwnerByTin(long tinNumber) {
        return ownerRepository.findById(tinNumber).orElseThrow(null);
    }

    @Override
    public Owner getOwnerByEmail(String email) {
        return ownerRepository.getOwnerByEmail(email);
    }

    @Override
    public Owner getOwnerByUsername(String username) {
        return ownerRepository.getOwnerByUsername(username);
    }

    @Override
    public boolean updateAddress(long tinNumber, String address) {
        return ownerRepository.updateAddress(tinNumber, address) == 1;
    }

    @Override
    public boolean updateEmail(long tinNumber, String email) {
        return ownerRepository.updateEmail(tinNumber, email) == 1;
    }

    @Override
    public boolean updatePassword(long tinNumber, String password) {
        return ownerRepository.updatePassword(tinNumber, password) == 1;
    }

    @Override
    public boolean deleteOwner(long tinNumber) {
        Owner owner = ownerRepository.findById(tinNumber).orElse(null);
        if (owner == null) return false;
        if (owner.getProperties().isEmpty() && owner.getRepairs().isEmpty()) {
            ownerRepository.deleteById(tinNumber);
            return true;
        } else {
            owner.setActive(false);
            ownerRepository.save(owner);
            return true;
        }
    }

    @Override
    public List<Owner> getAllData() {
        return ownerRepository.findAll();
    }

}
