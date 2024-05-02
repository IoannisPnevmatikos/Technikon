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
        if (ownerRepository.findById(ownerDto.tinNumber()).orElseThrow(null) == null) return null;
        Owner owner = new Owner(); // na ginei elegxos gia lathos
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
        Owner owner = ownerRepository.findById(tinNumber).orElseThrow(null);
        if (owner == null) return null;
        if (!owner.isActive()) return null;
        return owner;
    }

    @Override
    public Owner getOwnerByEmail(String email) {
        Owner owner = ownerRepository.getOwnerByEmail(email); // na ginei elegxos gia lathos
        if (owner == null) return null;
        if (!owner.isActive()) return null;
        return owner;
    }

    @Override
    public Owner getOwnerByUsername(String username) {
        Owner owner =  ownerRepository.getOwnerByUsername(username); // na ginei elegxos gia lathos
        if (owner == null) return null;
        if (!owner.isActive()) return null;
        return owner;
    }

    @Override
    public boolean updateAddress(long tinNumber, String address) {
        Owner owner = ownerRepository.findById(tinNumber).orElse(null);
        if (owner == null) return false;
        if (!owner.isActive()) return false;
        return ownerRepository.updateAddress(tinNumber, address) == 1;
    }

    @Override
    public boolean updateEmail(long tinNumber, String email) {
        Owner owner = ownerRepository.findById(tinNumber).orElse(null);
        if (owner == null) return false;
        if (!owner.isActive()) return false;
        return ownerRepository.updateEmail(tinNumber, email) == 1;
    }

    @Override
    public boolean updatePassword(long tinNumber, String password) {
        Owner owner = ownerRepository.findById(tinNumber).orElse(null);
        if (owner == null) return false;
        if (!owner.isActive()) return false;
        return ownerRepository.updatePassword(tinNumber, password) == 1;
    }

    @Override
    public boolean deleteOwner(long tinNumber) {
        Owner owner = ownerRepository.findById(tinNumber).orElse(null);
        if (owner == null) return false;
        if (owner.getProperties().isEmpty() && owner.getRepairs().isEmpty()) {
            ownerRepository.deleteById(tinNumber);
        } else {
            owner.setActive(false);
            ownerRepository.save(owner);
        }
        return true;
    }

    @Override
    public List<Owner> getAllData() {
        return ownerRepository.findAll();
    }

}
