package com.team1.technikon.service.impl;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.InvalidInputException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.model.Owner;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.securityservice.dto.ChangePwRequestDto;
import com.team1.technikon.securityservice.service.UserInfoDetails;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.team1.technikon.mapper.Mapper.mapToOwnerDto;
import static com.team1.technikon.validation.OwnerValidator.isValidOwner;
import static com.team1.technikon.validation.OwnerValidator.isValidSignUpDto;


@Service
@Primary
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class OwnerServiceImpl implements OwnerService, UserDetailsService {

    protected OwnerRepository ownerRepository;
    private PasswordEncoder encoder;
    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

    public OwnerServiceImpl() {

    }

    @Transactional
    @Override
    public OwnerDto createOwner(OwnerDto ownerDto) throws EntityFailToCreateException {
        try {
            if (isValidOwner(ownerDto)) {
                logger.info("Creating an owner {}", ownerDto);
                Owner owner = ownerRepository.findByUsername(ownerDto.username()).orElseThrow(() -> new EntityNotFoundException("Signed up user not found. Sign up first"));
                setUpdateFields(ownerDto, owner);
                return mapToOwnerDto(ownerRepository.save(owner));
            } else throw new EntityFailToCreateException("User Fields not valid. OR Not present " + ownerDto);
        } catch (Exception e) {
            throw new EntityFailToCreateException(e.getMessage());
        }
    }


    public String addUser(SignUpDto signUpDto) throws EntityFailToCreateException {
        Owner owner = new Owner();
        owner.setRole("USER");
        if (isValidSignUpDto(signUpDto)) {
            owner.setUsername(signUpDto.username());
            owner.setPassword(encoder.encode(signUpDto.password()));
            owner.setEmail(signUpDto.email());

            return ownerRepository.save(owner).toString();
        } else throw new EntityFailToCreateException("Check the sign up inputs again! ");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Owner> userDetail = ownerRepository.findByUsername(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    @Override
    public OwnerDto getOwnerById(Long authId, long id) throws EntityNotFoundException, UnauthorizedAccessException {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        if (authId != null && owner.getId() != authId)
            throw new UnauthorizedAccessException("You are unable to modify this entity.");
        return mapToOwnerDto(owner);
    }

    @Transactional
    @Override
    public OwnerDto updateOwner(Long authId, String username, OwnerDto ownerDto) throws UnauthorizedAccessException, InvalidInputException, EntityNotFoundException {

        Owner owner = ownerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Requested owner not found."));
        if (isValidOwner(ownerDto)) {
            if (authId != null && owner.getId() != authId)
                throw new UnauthorizedAccessException("You are unable to modify this entity.");
            setUpdateFields(ownerDto, owner);

        } else throw new InvalidInputException("User not valid. Check firstname,lastname, email and tin Num");
        try {
            ownerRepository.save(owner);
        } catch (Exception e) {
            throw new InvalidInputException("update failed to execute. Check inputs again");
        }
        return mapToOwnerDto(owner);

    }

    static void setUpdateFields(OwnerDto ownerDto, Owner owner) {
        if (ownerDto.username() != null) owner.setUsername(owner.getUsername());
        if(ownerDto.password() != null) owner.setPassword(owner.getPassword());
        //WHen sign up tin is null, set the tin which comes from ownerdto
        if (ownerDto.tinNumber() != null && owner.getTinNumber() == null) owner.setTinNumber(ownerDto.tinNumber());
        //When owner tin already exists, dont change
        else owner.setTinNumber(owner.getTinNumber());
        if (ownerDto.address() != null) owner.setAddress(ownerDto.address());
        if (ownerDto.firstName() != null) owner.setFirstName(ownerDto.firstName());
        if (ownerDto.lastName() != null) owner.setLastName(ownerDto.lastName());
        if (ownerDto.email() != null) owner.setEmail(ownerDto.email());
        if (ownerDto.phone() != null) owner.setPhone(ownerDto.phone());
    }


    @Override
    public String updateOwnerPassword(ChangePwRequestDto changePwRequestDto) throws EntityNotFoundException,InvalidInputException {
    logger.info("Trying to change password");
        Owner owner = ownerRepository.findByUsername(changePwRequestDto.getUsername()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if(!encoder.matches(changePwRequestDto.getNewPassword(), changePwRequestDto.getPassword())){
            logger.info("Password does not match. Can change");
            String encodedNewPw = encoder.encode(changePwRequestDto.getNewPassword());
            owner.setPassword(encodedNewPw);
            ownerRepository.updateOwnerPassword(encodedNewPw,changePwRequestDto.getUsername());
            return "Password changed";
        }
        else
            throw new InvalidInputException("SAME PASSWORD");

}

@Transactional
@Override
public boolean deleteOwnerByUsername(Long authId, String username) throws EntityNotFoundException {
    try {
        logger.info("Deleting an owner by username{}", username);
        Owner owner = ownerRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if(authId==owner.getId()) {
            if (owner.getProperties().isEmpty()) {
                owner.setActive(false);
                ownerRepository.save(owner);
                return true;
            } else {
                logger.info("With Username found completely Empty Owner.. deleting");
                ownerRepository.deleteByUsername(username);
                return true;
            }
        }
        else throw new UnauthorizedAccessException("You are not authorized to delete this entity.");

    } catch (Exception e) {
        throw new EntityNotFoundException(e.getMessage());
    }
}

@Transactional
@Override
public boolean deleteOwnerById(Long authId, Long id) throws EntityNotFoundException, UnauthorizedAccessException {

    Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Requested owner not found."));
    if (authId != null && owner.getId() != authId)
        throw new UnauthorizedAccessException("You are unable to modify this entity.");

    try {
        logger.info("With id, deleting an owner by id {}", id);

        if (!owner.getProperties().isEmpty()) {
            owner.setActive(false);
            ownerRepository.save(owner);
            return true;
        } else {
            logger.info("Completely Empty Owner deleting");
            ownerRepository.deleteById(id);
        }

        return false;

    } catch (Exception e) {
        throw new EntityNotFoundException(e.getMessage());
    }
}

    @Override
    public OwnerDto getOwnerByTin(Long authId, String tin) throws EntityNotFoundException,UnauthorizedAccessException {

        Owner owner = ownerRepository.findByTinNumber(tin).orElseThrow(() -> new EntityNotFoundException("Requested owner not found."));
        if(authId == owner.getId() )
         return mapToOwnerDto(owner);
        else throw new UnauthorizedAccessException("Your Tin Number is incorrect. Check again");
    }


}
