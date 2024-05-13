package com.team1.technikon.service.impl;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.mapper.TechnikonMapper;
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
    private final TechnikonMapper technikonMapper;
    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);


    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) throws OwnerFailToCreateException {
        try {
            logger.info("Creating an owner {}", ownerDto);
            return technikonMapper.toOwnerDto(ownerRepository.save(technikonMapper.toOwner(ownerDto)));
        } catch (Exception e) {
            throw new OwnerFailToCreateException(e.getMessage());

        }

    }

    @Override
    public OwnerDto getOwnerByTin(long tinNumber) throws OwnerNotFoundException {
        try {
            logger.info("Getting an owner with tin Number {}", tinNumber);
            return ownerRepository.findById(tinNumber).map(technikonMapper::toOwnerDto).get();
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public OwnerDto getOwnerByEmail(String email) throws OwnerNotFoundException {
        try {
            logger.info("Getting an owner by email {}", email);
            return ownerRepository.getOwnerByEmail(email).map(technikonMapper::toOwnerDto).get();
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());

        }
    }

    @Override
    public OwnerDto getOwnerByUsername(String username) throws OwnerNotFoundException {
        try {
            logger.info("Getting an owner with username {}", username);
            Optional<Owner> owner = ownerRepository.getOwnerByUsername(username); // na ginei elegxos gia lathos
            return owner.map(technikonMapper::toOwnerDto).get();
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updateAddress(long tinNumber, String address) throws OwnerNotFoundException {
        try {
            logger.info("Updating an owner's address {}", address);
            Optional<Owner> owner = ownerRepository.findById(tinNumber);
            if (owner.isEmpty() || !owner.get().isActive()) return false;
            return ownerRepository.updateAddress(tinNumber, address) == 1;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updateEmail(long tinNumber, String email) throws OwnerNotFoundException {
        try {
            logger.info("Updating an owner's email {}", email);
            Optional<Owner> owner = ownerRepository.findById(tinNumber);
            if (owner.isEmpty() || !owner.get().isActive()) return false;
            return ownerRepository.updateEmail(tinNumber, email) == 1;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updatePassword(long tinNumber, String password) throws OwnerNotFoundException {
        try {
            logger.info("Updating an owner's password {}", password);
            Optional<Owner> owner = ownerRepository.findById(tinNumber);
            if (owner.isEmpty() || !owner.get().isActive()) return false;
            return ownerRepository.updatePassword(tinNumber, password) == 1;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean deleteOwner(long tinNumber) throws OwnerNotFoundException {
        try {
            logger.info("Deleting an owner by tin number {}", tinNumber);
            Optional<Owner> owner = ownerRepository.findById(tinNumber);
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
            logger.info("Getting all owners.");
            return ownerRepository.findAll().stream().map(technikonMapper::toOwnerDto
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }

    }

}
