package com.team1.technikon.service.impl;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.OwnerFailToCreateException;
import com.team1.technikon.exception.OwnerNotFoundException;
import com.team1.technikon.mapper.Mapper;
import com.team1.technikon.model.Owner;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.securityservice.service.UserInfoDetails;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.team1.technikon.mapper.Mapper.mapToOwner;
import static com.team1.technikon.mapper.Mapper.mapToOwnerDto;


@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class OwnerServiceImpl implements OwnerService, UserDetailsService {

    private final OwnerRepository ownerRepository;
    private final PasswordEncoder encoder;
    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);


    @Override
    public OwnerDto getOwnerByUsername(String username) {
        return mapToOwnerDto(ownerRepository.findByUsername(username).get());
    }

    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) throws OwnerFailToCreateException {
        try {
            if (isValidOwner(ownerDto)) {
                logger.info("Creating an owner {}", ownerDto);
                Owner owner = mapToOwner(ownerDto);
                owner.setPassword(encoder.encode(ownerDto.password()));
                return mapToOwnerDto(ownerRepository.save(owner));
            } else throw new OwnerFailToCreateException("Validation failed! Check user input again. ");
        } catch (Exception e) {
            throw new OwnerFailToCreateException(e.getMessage());

        }

    }

    public String addUser(SignUpDto signUpDto) {
        Owner owner = new Owner();
        owner.setRole("USER");
        owner.setUsername(signUpDto.username());
        owner.setPassword(encoder.encode(signUpDto.password()));
        owner.setEmail(signUpDto.email());
        return ownerRepository.save(owner).toString();
    }

    @Override
    public String addAdmin(SignUpDto signUpDto) {
        Owner owner = new Owner();
        owner.setRole("ADMIN");
        owner.setUsername(signUpDto.username());
        owner.setPassword(encoder.encode(signUpDto.password()));
        owner.setEmail(signUpDto.email());
        return ownerRepository.save(owner).toString();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> userDetail = ownerRepository.findByUsername(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }


    @Override
    public OwnerDto getOwnerByTin(String tinNumber) throws OwnerNotFoundException {
        try {
            if (isValidTinNumber(tinNumber)) {
                logger.info("Getting an owner with tin Number {}", tinNumber);
                return mapToOwnerDto(ownerRepository.findByTinNumber(tinNumber).get());
            } else throw new OwnerNotFoundException("Invalid tin Number. Check tinNumber again.");
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
    public List<OwnerDto> getAllOwners() throws OwnerNotFoundException {
        try {
            logger.info("Getting all owners.");
            return ownerRepository.findAll().stream().map(Mapper::mapToOwnerDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }

    }

    @Override
    public List<OwnerDto> getAllActiveOwners() throws OwnerNotFoundException {
        try {
            logger.info("Getting all Activeowners.");
            return ownerRepository.findOwnersByIsActiveTrue().stream().map(Mapper::mapToOwnerDto
            ).collect(Collectors.toList());
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public OwnerDto updateOwner(String tinNumber, OwnerDto ownerDto) throws OwnerNotFoundException {
        try {
           return mapToOwnerDto(ownerRepository.save(mapToOwner(ownerDto)));
        } catch (Exception e) {
            throw new OwnerNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updateOwnerPassword(String username, String newPw) {
        return ownerRepository.updateOwnerPassword(username, encoder.encode(newPw)) == 1;
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

//    *********************VALIDATIONS*********************

    private boolean isValidTinNumber(String tinNumber) {
        String regexPattern = "^[0-9]{9}$";
        return Pattern.compile(regexPattern).matcher(tinNumber).matches();
    }

    private boolean isValidPhone(String phone) {
        return phone.length() == 10;
    }

    private boolean isValidEmail(String email) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    private boolean isValidUsername(String username) {
        String regexPattern = "^[A-Za-z]\\w{5,29}$";
        return Pattern.compile(regexPattern).matcher(username).matches();
    }

    private boolean isValidName(String firstName, String lastName) {
        String regexPattern = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
        return Pattern.compile(regexPattern).matcher(firstName).matches() && Pattern.compile(regexPattern).matcher(lastName).matches();
    }

    private boolean isValidOwner(OwnerDto ownerDto) {
        return (isValidEmail(ownerDto.email()) &&
                isValidUsername(ownerDto.username()) &&
                isValidName(ownerDto.firstName(), ownerDto.lastName()) && isValidTinNumber(ownerDto.tinNumber()) &&
                isValidPhone(ownerDto.phone()));
    }

}
