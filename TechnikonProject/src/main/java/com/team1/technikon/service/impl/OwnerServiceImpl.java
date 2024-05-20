package com.team1.technikon.service.impl;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.mapper.MapperTemp;
import com.team1.technikon.model.Owner;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.team1.technikon.mapper.MapperTemp.mapToOwnerDto;
import static com.team1.technikon.mapper.MapperTemp.mapToOwner;


@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);


    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) throws OwnerFailToCreateException {
        try {
            if (isValidOwner(ownerDto)) {
                logger.info("Creating an owner {}", ownerDto);
                return mapToOwnerDto(ownerRepository.save(mapToOwner(ownerDto)));
            } else throw new OwnerFailToCreateException("Validation failed! Check user input again. ");
        } catch (Exception e) {
            throw new OwnerFailToCreateException(e.getMessage());

        }

    }

    @Override
    public OwnerDto getOwnerByTin(String tinNumber) throws OwnerNotFoundException {
        try {
            if (isValidTinNumber(tinNumber)) {
                logger.info("Getting an owner with tin Number {}", tinNumber);
                return mapToOwnerDto(ownerRepository.findByTinNumber(tinNumber).get());
            }
            else throw new  OwnerNotFoundException("Invalid tin Number. Check tinNumber again.");
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public OwnerDto getOwnerByEmail(String email) throws OwnerNotFoundException {
        return mapToOwnerDto(ownerRepository.findOwnerByEmail(email).get());
    }

    @Override
    public OwnerDto getOwnerByFirstName(String firstName) throws OwnerNotFoundException {
        return mapToOwnerDto(ownerRepository.findOwnerByFirstName(firstName).get());
    }

    @Override
    public OwnerDto getOwnerByLastName(String lastName) throws OwnerNotFoundException {
        return mapToOwnerDto(ownerRepository.findOwnerByLastName(lastName).get());
    }

    @Override
    public boolean updateAddress(String tinNumber, String address) throws OwnerNotFoundException {
        try {
            if (ownerRepository.updateAddress(tinNumber, address) == 1)
                return true;
            else throw new OwnerNotFoundException("Update failed! Check tinNumber again. ");
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updateOwnerByPhone(String tinNumber, String phoneNumber) throws OwnerNotFoundException {
        try {
            if (ownerRepository.updateOwnerByPhone(tinNumber, phoneNumber) == 1)
                return true;
            else throw new OwnerNotFoundException("Update failed! Check tinNumber again. ");
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean deleteOwner(String tinNumber) throws OwnerNotFoundException {
        try {
            logger.info("Deleting an owner by tin number {}", tinNumber);
            Optional<Owner> owner = ownerRepository.findByTinNumber(tinNumber);
            // long result = 0;
            if (owner.isPresent() && owner.get().getProperties().isEmpty()) {
                logger.info("Owner found to delete");
                ownerRepository.deleteByTinNumber(tinNumber);
            } else {
                owner.get().setActive(false);
                ownerRepository.save(owner.get());
                //  result = 1;
            }
            return true;
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<OwnerDto> getAllOwners() throws OwnerNotFoundException {
        try {
            logger.info("Getting all owners.");
            return ownerRepository.findAll().stream().map(MapperTemp::mapToOwnerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }

    }

    @Override
    public List<OwnerDto> getAllActiveOwners() throws OwnerNotFoundException {
        try {
            logger.info("Getting all Activeowners.");
            return ownerRepository.findOwnersByIsActiveTrue().stream().map(MapperTemp::mapToOwnerDto
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

//    *********************VALIDATIONS*********************

    private boolean isValidOwner(OwnerDto ownerDto) {
        return isValidTinNumber(ownerDto.tinNumber()) &&
                isValidPhone(ownerDto.phone());
    }

    private boolean isValidTinNumber(String tinNumber) {
        String regexPattern = "^[0-9]{9}$";
        return Pattern.compile(regexPattern).matcher(tinNumber).matches();
    }

    private boolean isValidPhone(String phone) {
        return phone.length() == 10;
    }


}
