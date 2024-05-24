package com.team1.technikon.service.impl;

import com.team1.technikon.dto.OwnerDto;
import com.team1.technikon.dto.SignUpDto;
import com.team1.technikon.exception.EntityFailToCreateException;
import com.team1.technikon.exception.EntityNotFoundException;
import com.team1.technikon.exception.UnauthorizedAccessException;
import com.team1.technikon.model.Owner;
import com.team1.technikon.repository.OwnerRepository;
import com.team1.technikon.securityservice.service.UserInfoDetails;
import com.team1.technikon.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
import static com.team1.technikon.mapper.Mapper.mapToOwner;
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
            if ( isValidSignUpDto(new SignUpDto(ownerDto.username(),ownerDto.password(),ownerDto.email()))&&ownerRepository.findByUsername(ownerDto.username()).isPresent()) {
                logger.info("Creating an owner {}", ownerDto);
                Long id = ownerRepository.findByUsername(ownerDto.username()).get().getId();
                logger.info("id was found is : {}",id);
               Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Requested owner not found."));
               owner.setPassword(encoder.encode(ownerDto.password()));
               if (ownerDto.tinNumber()!=null) owner.setTinNumber(ownerDto.tinNumber());
               if (ownerDto.username()!=null) owner.setUsername(ownerDto.username());
               if (ownerDto.address()!=null) owner.setAddress(ownerDto.address());
               if (ownerDto.firstName()!=null) owner.setFirstName(ownerDto.firstName());
               if (ownerDto.lastName()!=null) owner.setLastName(ownerDto.lastName());
               if (ownerDto.email()!=null) owner.setEmail(ownerDto.email());
               if (ownerDto.phone()!=null) owner.setPhone(ownerDto.phone());

                return mapToOwnerDto(ownerRepository.save(owner));
           } else throw new EntityFailToCreateException("User not present. ");
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
    public OwnerDto getOwnerById(Long authId,long id) throws EntityNotFoundException,UnauthorizedAccessException {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        if (authId!=null && owner.getId()!=authId) throw new UnauthorizedAccessException("You are unable to modify this entity.");
        return mapToOwnerDto(owner);
    }

    @Transactional
    @Override
    public OwnerDto updateOwner(Long authId, Long ownerId, OwnerDto ownerDto) throws UnauthorizedAccessException, EntityFailToCreateException, EntityNotFoundException {

        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new EntityNotFoundException("Requested owner not found."));
        if (authId!=null && owner.getId()!=authId) throw new UnauthorizedAccessException("You are unable to modify this entity.");
        if(ownerDto.tinNumber()!=null) owner.setTinNumber(ownerDto.tinNumber());
        if (ownerDto.username()!=null) owner.setUsername(ownerDto.username());
        if (ownerDto.address()!=null) owner.setAddress(ownerDto.address());
        if (ownerDto.firstName()!=null) owner.setFirstName(ownerDto.firstName());
        if (ownerDto.lastName()!=null) owner.setLastName(ownerDto.lastName());
        if (ownerDto.email()!=null) owner.setEmail(ownerDto.email());
        if (ownerDto.phone()!=null) owner.setPhone(ownerDto.phone());
        isValidOwner(mapToOwnerDto(owner));
        try {
            ownerRepository.save(owner);
        } catch (Exception e) {
            throw new EntityFailToCreateException("update failed to execute. Check again");
        }
        return mapToOwnerDto(owner);

    }



    @Override
    public boolean updateOwnerPassword(String username, String newPw) {
        return ownerRepository.updateOwnerPassword(username, encoder.encode(newPw)) == 1;
    }

    @Transactional
    @Override
    public boolean deleteOwnerByTin(String tinNumber) throws EntityNotFoundException {
        try {
            logger.info("Deleting an owner by tin number {}", tinNumber);
            Optional<Owner> owner = ownerRepository.findByTinNumber(tinNumber);
            // long result = 0;

            if (owner.isPresent() && owner.get().getProperties().isEmpty()) {
                owner.get().setActive(false);
                ownerRepository.save(owner.get());
                return true;
            } else {
                logger.info("With Tin found completely Empty Owner deleting");
                ownerRepository.deleteByTinNumber(tinNumber);
            }

            return false;

        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteOwnerById(Long id) throws EntityNotFoundException {
        try {
            logger.info("With id, deleting an owner by id {}", id);
            Optional<Owner> owner = ownerRepository.findById(id);
            // long result = 0;

            if (owner.isPresent() && owner.get().getProperties().isEmpty()) {
                owner.get().setActive(false);
                ownerRepository.save(owner.get());
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


}
