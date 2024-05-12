package com.team1.technikon.service.impl;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.mapper.ObjectMapper;
import com.team1.technikon.model.Owner;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final ObjectMapper objectMapper;
    private static  Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);



    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) throws OwnerFailToCreateException {
        try{
            return objectMapper.toOwnerDto(ownerRepository.save(objectMapper.toOwner(ownerDto)));
        }
        catch (Exception e){
            throw new OwnerFailToCreateException(e.getMessage());

        }

    }

    @Override
    public OwnerDto getOwnerByTin(long tinNumber) throws OwnerNotFoundException{
        try {
            return ownerRepository.findById(tinNumber).map(objectMapper::toOwnerDto).get();
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public OwnerDto getOwnerByEmail(String email) throws OwnerNotFoundException{
        try {
            return ownerRepository.getOwnerByEmail(email).map(objectMapper::toOwnerDto).get();
        }
        catch (Exception e){
            throw new OwnerNotFoundException(e.getMessage());

        }
    }

    @Override
    public OwnerDto getOwnerByUsername(String username) throws OwnerNotFoundException{
        try {
            Optional<Owner> owner =  ownerRepository.getOwnerByUsername(username); // na ginei elegxos gia lathos
            return owner.map(objectMapper::toOwnerDto).get();
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updateAddress(long tinNumber, String address) throws OwnerNotFoundException {
        try {
           Optional <Owner> owner = ownerRepository.findById(tinNumber);
            if (owner.isEmpty() ||!owner.get().isActive()) return false;
                return ownerRepository.updateAddress(tinNumber, address) == 1;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updateEmail(long tinNumber, String email) throws OwnerNotFoundException {
        try {
            Optional <Owner> owner = ownerRepository.findById(tinNumber);
            if (owner.isEmpty() ||!owner.get().isActive()) return false;
            return ownerRepository.updateEmail(tinNumber, email) == 1;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updatePassword(long tinNumber, String password) throws OwnerNotFoundException {
        try {
            Optional <Owner> owner = ownerRepository.findById(tinNumber);
            if (owner.isEmpty() ||!owner.get().isActive()) return false;
            return ownerRepository.updatePassword(tinNumber, password) == 1;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean deleteOwner(long tinNumber) throws OwnerNotFoundException{
        try {
            Optional <Owner> owner = ownerRepository.findById(tinNumber);
            if (owner.isEmpty()) return false;
            if (owner.get().getProperties().isEmpty()) {
                ownerRepository.deleteById(tinNumber);
            } else {
                owner.get().setActive(false);
                ownerRepository.save(owner.get());
            }
            return true;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<OwnerDto> getAllData() throws OwnerNotFoundException {
        try {
            return ownerRepository.findAll().stream().map(objectMapper::toOwnerDto
            ).collect(Collectors.toList());
        }
        catch (Exception e){
            throw new OwnerNotFoundException(e.getMessage());
        }

    }

}
