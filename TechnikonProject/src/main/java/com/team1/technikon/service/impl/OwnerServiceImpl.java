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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final TechnikonMapper technikonMapper;
    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);


    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) throws OwnerFailToCreateException {
        try {
            if (isValidOwner(ownerDto)) {
                logger.info("Creating an owner {}", ownerDto);
                return technikonMapper.toOwnerDto(ownerRepository.save(technikonMapper.toOwner(ownerDto)));
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
                return ownerRepository.findByTinNumber(tinNumber).map(technikonMapper::toOwnerDto).get();
            } else throw new OwnerNotFoundException("Invalid tin Number. Check tinNumber again.");
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
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
    public List<OwnerDto> getAllData() throws OwnerNotFoundException {
        try {
            logger.info("Getting all owners.");
            return ownerRepository.findAll().stream().map(technikonMapper::toOwnerDto
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }

    }
//
//    @Override
//    public List<OwnerDto> getAllActiveOwners() throws OwnerNotFoundException {
//        try {
//            logger.info("Getting all Activeowners.");
//            return ownerRepository.findOwnersByActiveTrue().stream().map(technikonMapper::toOwnerDto
//            ).collect(Collectors.toList());
//        } catch (Exception e) {
//            throw new OwnerNotFoundException(e.getMessage());
//        }
//    }

//    *********************VALIDATIONS*********************

    private boolean isValidOwner(OwnerDto ownerDto) {
        return isValidTinNumber(ownerDto.tinNumber()) &&
//                isValidEmail(ownerDto.email()) &&
                isValidPhone(ownerDto.phone());
//                isValidUsername(ownerDto.username()) &&
//                isValidName(ownerDto.firstName(), ownerDto.lastName());
    }

    private boolean isValidTinNumber(String tinNumber) {
        String regexPattern = "^[0-9]{9}$";
        return Pattern.compile(regexPattern).matcher(tinNumber).matches();
    }

//    private boolean isValidEmail(String email) {
//        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
//        return Pattern.compile(regexPattern).matcher(email).matches();
//    }

    private boolean isValidPhone(String phone) {
        return phone.length() == 10;
    }
//
//    private boolean isValidUsername(String username) {
//        String regexPattern = "^[A-Za-z]\\w{5,29}$";
//        return Pattern.compile(regexPattern).matcher(username).matches();
//    }
//
//    private boolean isValidName(String firstName, String lastName) {
//        String regexPattern = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
//        return Pattern.compile(regexPattern).matcher(firstName).matches() && Pattern.compile(regexPattern).matcher(lastName).matches();
//    }


}
